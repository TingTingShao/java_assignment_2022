package Entities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import Services.CopyCorner;

public class Repository {
	
	//private variable backupDate 
	private Date backupDate;
	
	//has a private CopyCorner subject cc
	private CopyCorner cc = new CopyCorner();
	
	public void createBackup() {
		
		// Set the backup date and time
		backupDate = new Date();
		
		File directory = new File("./repository");
		
		//create a backup directory
		File backupFolder = new File("./backupRepository");

		if(!backupFolder.exists()) {
			backupFolder.mkdir();
		}

		//put all the file into a list
		File[]files = directory.listFiles();

		//loop through all the files in the repository directory
		for(File file:files) {
			try {

				//copy all the files from the repository to a new directory, backupRepository
				cc.copyFile(file, new File(backupFolder + "/" + file.getName()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}System.out.println("repository is backed up!");
	}

	//get backup date 
	public Date getBackupDate() {
		System.out.println("backup date is: ");
		return this.backupDate;
	}
	
	
	public void restoreRepository() {
		
		//first clear all the contents in the repository
		clearRepository();
		
		File currentFolder = new File("./repository");
		File backupFolder = new File("./backupRepository");
		
		//check if the backup folder is built already
		if(!backupFolder.exists()) {
			System.out.println("backupRepository does not exsit, restore failed");
			}
		
		//put all the files in backup folder into a list
		File[]files = backupFolder.listFiles();
		
		//loop through all the files in the backup folder, and copy them into the repository folder
		for(File file:files) {
			try {
				cc.copyFile(file, new File(currentFolder + "/" + file.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void clearRepository() {
		
		File currentFolder = new File("./repository");
		
		File[] currentFiles = currentFolder.listFiles();
		for (File file : currentFiles) {
			file.delete();
		}
	}
	
}
