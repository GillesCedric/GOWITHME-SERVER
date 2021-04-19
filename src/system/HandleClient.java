package system;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import utilities.Keywords;
import utilities.Utilitie;

public class HandleClient implements Runnable {
	private Socket clientSocket;
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;

	public HandleClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
			Utilitie.error(HandleClient.class.getName(), e);
		}
	}

	@Override
	public void run() {
		System.err.println("Lancement du traitement de la connexion cliente");

		boolean isRunnig = true;
		// tant que la connexion est active, on traite les demandes
		while (isRunnig) {
			// On attend la demande du client
			Handler<?> request = read();
			//System.out.println("Message recu du client");
			// On traite la demande du client en fonction de la commande envoyée
			Handler<?> toSend = null;
			if(request != null) {
				switch (request.getAction()) {
				case addImageUser:
					GestionFile.saveFile((byte[])request.getData(), true,request.getName());
					toSend = new Handler<String>(Keywords.success, "Image enregistréé avec succès");
					break;
				case getImageUser:
					byte[] user = GestionFile.getFile(true,request.getName());
					toSend = new Handler<byte[]>(Keywords.image, user);
					break;
				case addImageCar:
					GestionFile.saveFile((byte[])request.getData(), false,request.getName());
					toSend = new Handler<String>(Keywords.success, "Image enregistréé avec succès");
					break;
				case getImageCar:
					byte[] car = GestionFile.getFile(false,request.getName());
					toSend = new Handler<byte[]>(Keywords.image, car);
					break;
				case close:
					isRunnig = false;
					break;
				default:
					toSend = new Handler<String>(Keywords.error, "Erreur : action inconnu");
					break;
				}
				
				if (!isRunnig) {
					close();
					break;
				}
				
				// On envoie la réponse au client
				write(toSend);
				
			}
		}
	}

	private Handler<?> read() {
		try {
			return (Handler<?>) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			Utilitie.error(HandleClient.class.getName(), e);
		}
		return null;
	}

	public void write(Handler<?> request) {
		try {
			// On envoie la commande au serveur
			objectOutputStream.writeObject(request);
	
			// TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
			objectOutputStream.flush();
	
			System.out.println("Commande " + request.getAction() + " envoyée au client");
		} catch (IOException e) {
			Utilitie.error(HandleClient.class.getName(), e);
		}
	}

	private void close() {
		System.err.println("Fermeture de la connexion au client ! ");
		objectOutputStream = null;
		objectInputStream = null;
		try {
			clientSocket.close();
		} catch (IOException e) {
			Utilitie.error(HandleClient.class.getName(), e);
		}
	}

}
