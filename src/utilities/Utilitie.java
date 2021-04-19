package utilities;

import system.Log;


public class Utilitie {
	
	public static int generateNumber(int min,int max) {
		return  min + (int) (Math.random() * ((max - min) + 1));
	}
	
	public static void error(String className,Exception ex) {
		Log.addLog(new Log(className,ex.toString()+" : "+ex.getMessage()));
   	 	System.err.println(ex.getMessage());
	}

}