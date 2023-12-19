package es.um.fcd.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class AppLogger {

	public static void logError(String error){
		log(error, "LogError.txt");
		System.out.println(error);
	}
	
	public static void logException(Throwable exception){
		Writer result = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(result);
	    exception.printStackTrace(printWriter);
		
		log(result.toString(), "LogException.txt");
		
		System.out.println(result.toString());
	}
	
	public static void log(String info){
		log(info, "Log.txt");
		
		System.out.println(info);
	}
	
	public static void log(String info, String ficheroLog){
		/*try{
			
    		File fichero = new File(ficheroLog);
    		
    		System.out.println( fichero.getAbsoluteFile());

    		//Si el fichero no existe se crea.
    		if(!fichero.exists()) fichero.createNewFile();

    		FileWriter fileWritter = new FileWriter(fichero.getName(),true);
    		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    		bufferWritter.write(info);
    		bufferWritter.close();
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}*/
		
	}

}
