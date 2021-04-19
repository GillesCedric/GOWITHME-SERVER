package system;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.Utilitie;

/*
 * @since 28/11/2020
 * @authors ANOUMEDEM NGUEFACK Gilles Cédric, NSIA FOTUE Rene, TCHITAKE GNIZE Alain, WELEHELA Patricia
 * @class GestionFile
 * @description Cette classe permet la gestion des fichiers nécéssaires à l'application
 * @public
 */
public class GestionFile {
	private ArrayList<String> files = new ArrayList<String>();
    /*
     * @constructor
     * @description constructeur permettant d'ajouter des fichiers sur une liste
     * @public
     */
    public GestionFile() {
    	files.add(Data.LOG);
    	files.add(Data.USERS);
        files.add(Data.LOGS);
        files.add(Data.PROFILES);
        files.add(Data.CARS);
    }

    /*
     * @private
     * @method isExist
     * @returns boolean
     * @param File file le fichier dont on va tester l'existence
     * @description cette méthode permet de verifier l'existence d'un fichier
     */
    private boolean isExist(File file) {
        return file.exists();
    }

    /*
     * @public
     * @method writeChars
     * @returns void
     * @param String filename le fichier dans lequel seront ecris les caracteres, String data les caracteres qui seront à ecrire dans le fcihier
     * throws IOException
     * @description cette méthode permet de ecrire les caracteres dans un fichier
     */
    public static void writeChars(String data) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR + Data.LOG,true)))) {
            printWriter.println(data);
        } catch (IOException ex) {
 			Utilitie.error(GestionFile.class.getName(), ex);
        }
    }
    
    /*
     * @public
     * @method readChars
     * @returns ArrayList<String>
     * @param String filename le fichier dans lequel seront lus les caracteres
     * @throws IOException
     * @description cette méthode permet de lire les caracteres dans un fichier
     */
    public static ArrayList<String> readChars() {
        ArrayList<String> logs = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR + Data.LOG)))) {
            while (scanner.hasNextLine()){
                logs.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
        	
        }
        return logs;
    }

    /*
     * @private
     * @method createFile
     * @returns void
     * @throws IOException, ClassNotFoundException, InterruptedException, NoSuchAlgorithmException
     * @description cette méthode permet de creer les fichiers
     */
    private void createDirectory() {
    	for (String filename : this.files) {
    		File file = new File(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR + filename);
            if (!file.exists()) {
            	if(Data.LOG.equals(filename)) {
            		try {
						if (!file.createNewFile()) {
							Utilitie.error(GestionFile.class.getName(), new Exception("Impossible de créer le fichier "+Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+filename));
						}
					} catch (IOException e) {
						Utilitie.error(GestionFile.class.getName(), e);
					}
            	}else {
            		if (!file.mkdir()) {
    					Utilitie.error(GestionFile.class.getName(), new Exception("Impossible de créer le répertoire "+Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+filename));
    				}
            	}
            }
    	}
    }

	/*
     * @public
     * @method run
     * @returns String
     * @throws IOException, ClassNotFoundException, InterruptedException, NoSuchAlgorithmException
     * @description cette méthode permet de creer un fichier dans un repertoire si ce repertoire existe sinon creer d'abord le repertoire
     */
    public void run() {
        File directory = new File(Data.LOCATION+ Data.APPNAME);
        //File directoryUsers = new File(Data.LOCATION+ Data.APPNAME +Data.SEPARATOR+);
        if (isExist(directory)) {
        	createDirectory();
        } else {
            if (directory.mkdir()) {
            	createDirectory();
            } else {
    			Utilitie.error(GestionFile.class.getName(), new Exception("Impossible de créer les fichiers de base de l'application"));
            }
        }
    }
    
    public static byte[] getFile(boolean type,String name) {
    	FileInputStream fileInputStream;
    	try {
    		if(type)
    			fileInputStream = new FileInputStream(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+Data.PROFILES+Data.SEPARATOR+name);
    		else
    			fileInputStream = new FileInputStream(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+Data.CARS+Data.SEPARATOR+name);
			byte b[] = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			fileInputStream.close();
			return b;
		} catch (IOException e) {
			Utilitie.error(GestionFile.class.getName(), e);
		}
    	return null;
    }
    
    public static void saveFile(byte[] file,boolean type,String name) {
    	FileOutputStream fileOutputStream;
		try {
			if(type)
				fileOutputStream = new FileOutputStream(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+Data.PROFILES+Data.SEPARATOR+name);
			else
				fileOutputStream = new FileOutputStream(Data.LOCATION + Data.APPNAME+ Data.SEPARATOR+Data.CARS+Data.SEPARATOR+name);
			fileOutputStream.write(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
