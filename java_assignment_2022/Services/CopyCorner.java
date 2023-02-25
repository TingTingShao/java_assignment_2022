package Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//create a class to contain the method: copy file from one directory to another directory
public class CopyCorner {
	
	//reference: 
	//https://stackoverflow.com/questions/7353871/fileoutputstream-throws-filenotfoundexception-when-unzipping
	
	public Boolean copyFile(File source, File destination) throws FileNotFoundException, IOException {
		
		//copy source
		try (FileInputStream in = new FileInputStream(source);
				
				//paste destination
				FileOutputStream out = new FileOutputStream(destination)) {
			byte[] buffer = new byte[1024];			
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			return true;
		}	catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}	
	}
}
