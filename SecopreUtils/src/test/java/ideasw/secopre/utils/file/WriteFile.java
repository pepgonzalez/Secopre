package ideasw.secopre.utils.file;

import java.io.File;
import java.io.IOException;

public class WriteFile {
    public static void main( String[] args )
    {	
    	try {
    		 
	      File file = new File("/SecopreResources/shellscript.sh");
	      
	      file.setExecutable(true, false);
	      file.setReadable(true, false);
	      file.setWritable(true, false);
	      
	      if(file.exists()){
	    	  System.out.println("Is Execute allow : " + file.canExecute());
		  System.out.println("Is Write allow : " + file.canWrite());
		  System.out.println("Is Read allow : " + file.canRead());
	      }
	         

	      
	      System.out.println("Is Execute allow : " + file.canExecute());
	      System.out.println("Is Write allow : " + file.canWrite());
	      System.out.println("Is Read allow : " + file.canRead());
	      
	      if (file.createNewFile()){
	        System.out.println("File is created!");
	      }else{
	        System.out.println("File already exists.");
	      }
	      
    	} catch (IOException e) {
	      e.printStackTrace();
	    }
    }
}
