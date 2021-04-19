package application;

import system.Data;
import system.GestionFile;
import system.Server;

public class Main{
	public static void main(String[] args) {
		new GestionFile().run();
		new Server(Data.PORT,Data.HOST).launch();
	}
}
