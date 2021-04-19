package system;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import utilities.Utilitie;

public class Server {
	private int nbClients = 0;
	private int listenPort;
	private InetAddress inetAddress;
	private ServerSocket serverSocket;
	private boolean isRunning = true;

	public Server(int listenPort, String inetAddress) {
		this.listenPort = listenPort;
		try {
			this.inetAddress = InetAddress.getByName(inetAddress);
		} catch (UnknownHostException e) {
			Utilitie.error(Server.class.getName(), e);
		}
	}

	public void launch() {
		System.out.println("Lancement du serveur");
		try {
			serverSocket = new ServerSocket(this.listenPort,50,this.inetAddress);
			System.out.println("Serveur démarrer à l'adresse " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
			// Toujours dans un thread à part vu qu'il est dans une boucle infinie
			Thread t = new Thread(new Runnable() {
				public void run() {
					while (isRunning == true) {
						
						// On attend une connexion d'un client
						try {
							Socket client = serverSocket.accept();
							// Une fois reçue, on la traite dans un thread séparé
							System.out.println("Connexion cliente reçue.");
							nbClients++;
							Thread t = new Thread(new HandleClient(client));
							t.start();
						} catch (IOException e) {
							Utilitie.error(Server.class.getName(), e);
						}
					}
					try {
						serverSocket.close();
					} catch (IOException e) {
						Utilitie.error(Server.class.getName(), e);
					}
				}
			});
			t.start();
		} catch (IOException e) {
			Utilitie.error(Server.class.getName(), e);
		}
	}
	
	public void stop() {
        isRunning = false;
    }

	public int getNbClients() {
		return nbClients;
	}

	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}

	public int getListenPort() {
		return listenPort;
	}

	public void setListenPort(int listenPort) {
		this.listenPort = listenPort;
	}

	public InetAddress getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public String toString() {
		return "Server [nbClients=" + nbClients + ", listenPort=" + listenPort + ", inetAddress=" + inetAddress
				+ ", serverSocket=" + serverSocket + ", isRunning=" + isRunning + "]";
	}

}
