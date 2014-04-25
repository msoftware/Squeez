package csci567.squeez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveManager {

	public static Status Zip(ArrayList<String> files, String archive) {
		
		assert(false);
		return Status.OK;
	}
	
	public static Status Unzip(ArrayList<String> files, String archive) {
		
		//Build the destination path
		String[] name = archive.split("/");
		String destination = "";
		for (int i = 0; i < name.length-1; i++) {
			destination += name[i] + "/";
		}
		
		return Unzip(files, archive, destination); //unzip to current directory
	}
	
	public static Status Unzip(ArrayList<String> files, String archive, String destination) {
		assert(archive != destination);
		
		//Make sure the archive exists
		File archiveFile = new File(archive);
		if (!archiveFile.isFile()) {
			return Status.NOT_FILE;
		}
		
		//Make sure the destination path is fine
		File destinationFile = new File(destination);
		if (!destinationFile.exists()) {
			if (!destinationFile.mkdirs()) { //create the missing folders
				return Status.COULD_NOT_UNZIP;
			}
		} else if (!destinationFile.isDirectory()) {
			return Status.DESTINATION_NOT_DIRECTORY;
		}
		
		//Open the archive
		FileInputStream archiveFileStream = null;
		ZipInputStream zip = null;
		ZipEntry zipEntry = null;
		try {
			archiveFileStream = new FileInputStream(archive);
			zip = new ZipInputStream(archiveFileStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Status.DOES_NOT_EXIST;
		}
		
		//Perform unzip
		try {
			while ((zipEntry = zip.getNextEntry()) != null) {
				//Unzip each entry here
				assert(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				archiveFileStream.close();
				zip.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return Status.COULD_NOT_UNZIP;
		}
		
		//Close the streams
		try {
			archiveFileStream.close();
			zip.close();
		} catch (IOException e) {
			e.printStackTrace();
			return Status.COULD_NOT_UNZIP;
		}
		
		FileManager.List(destination, files); //refresh the contents of the current directory
		assert(false);
		return Status.OK;
	}
	
}
