package Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Services.CopyCorner;
import Services.Login;


//this class contains all the methods for team leaders
public class Document {
	
	//has a private CopyCorner subject cc
	private CopyCorner cc = new CopyCorner();

	//method change optimal alignment
	public boolean updateOptimalAlignment(String fileName) throws FileNotFoundException, IOException {

		//check if the repository has such file
		File from = new File(fileName);

		if (!from.exists()) {

			System.out.println(fileName+" hasn't submitted report");
		}

		//after checking, if the file exists, now start copying
		File to = new File("./repository/optimalAlignment.fasta");
		try {
			cc.copyFile(from, to);
			
			//make sure each time when update the optimal alignment, SNiP alignment will also get updated
			updateSNiP(fileName);
			
			System.out.println("Promote alignment from " + fileName + " to optimal alignment, and coresponding SNiP alignment is updated");
		} catch (IOException e) {

			//if the file format is not correct, show error
			System.out.println("An error occurred while copying the file: " + e.getMessage());
			return false;
		}
		return true;

	}
	
	//method change shared alignment
	public boolean updateSharedAlignment(String fileName) throws FileNotFoundException, IOException {
		
		//check if the repository has such file
		File from = new File(fileName);
		
			if (!from.exists()) {
				
				System.out.println(fileName+" hasn't submitted report");
			}
		
		//after checking if the file exists, now start copying
		File to = new File("./sharedAlignment.fasta");
		try {
			cc.copyFile(from, to);
			System.out.println("Promote alignment from " + fileName + " to shared alignment");
			return true;
		} catch (IOException e) {
			
			//if the file format is not correct, show error
			System.out.println("An error occurred while copying the file: " + e.getMessage());
			return false;
		}
		

	}
	
	
	//method merge report
	public boolean mergeReport()throws IOException{
		//has a login subject li
		Login li = new Login();
		
		String mergeReportName;
		String mergeAlignmentName;
		String mergeScoreName;
		
		//get report name from the information provided by user after logging in
		mergeReportName = li.getName();
		
		//file to be merged are from repository
		File directory = new File("./repository");
		
		// Set the name and path of the output file
		mergeAlignmentName=(mergeReportName+".alignment.txt");
		mergeScoreName=(mergeReportName+".score.txt");
		
		File outputFile1 = new File(mergeAlignmentName);
		File outputFile2 = new File(mergeScoreName);
		
		// Create a BufferedWriter to write to the output file
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(outputFile1));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFile2));
        
		// Get a list of all the files in the directory
		File[] files = directory.listFiles();
		
		// Iterate over the files
		for(File file:files) {
			
			// Check if there files with .alignment.txt extension
			if (file.getName().endsWith(".alignment.txt")) {
				
				System.out.println("submitted files are \n" + file);
				BufferedReader reader1 = new BufferedReader(new FileReader(file));
				
				//get each bioinformaticians' name
				 String first_word = file.getName().split("\\.")[0];
				 
				 //write into the file
                 writer1.write(first_word);
                 writer1.newLine();
                 
				// Read and write each line from the file to the output file
				String line;
				while((line=reader1.readLine())!=null) {
					writer1.write(line);
					writer1.newLine();		
				}
				reader1.close();
			}
			
			// Check if there are files with .alignment.txt extension
			if (file.getName().endsWith(".score.txt")) {
				
				BufferedReader reader2 = new BufferedReader(new FileReader(file));
				
				//get each bioinformaticians' name
				String first_word = file.getName().split("\\.")[0];
				
				System.out.print(first_word+"'s alignment score = ");
				
				 //write into the file
                writer2.write(first_word);
                writer2.newLine();
                
				// Read and write each line from the file to the output file
				String line;
				while((line=reader2.readLine())!=null) {
					
					writer2.write(line);
					
					//print the score
					System.out.println(line);
					
					writer2.newLine();		
				}
				reader2.close();
			}
		}
		System.out.println("Reports are merged!");
		writer1.close();
		writer2.close();
		return true;
	}
	
	private boolean updateSNiP(String fileName) throws FileNotFoundException, IOException {

		//read file
		try (BufferedReader reader = new BufferedReader(new 
				FileReader(fileName))) {
			BufferedWriter writer = new BufferedWriter(new 
					FileWriter("./repository/SNiPalignment.fasta"));

			//read the first sequence and store it as the reference sequence
			String line = reader.readLine(); //first genome ID
			writer.write(line);
			writer.newLine();


			String reference =reader.readLine(); //first genome sequence
//			System.out.println(reference);
			writer.write(reference);
			writer.newLine();

			//if line starts with >, write and print
			while((line=reader.readLine())!=null){
				if(line.startsWith(">")) {
//					System.out.println(line);
					writer.write(line);
					writer.newLine();
				}

				//otherwise, compare the sequence with the reference and print the result
				else {

					//reconstruct the sequence string
					StringBuilder modifiedLine = new StringBuilder();

					for(int i = 0; i<line.length(); i++) {
						char c = line.charAt(i);
						if(c == reference.charAt(i)) {
							
							//compared with reference genome, if char is same, replace with .
							modifiedLine.append(".");
						}
						else{	
							//otherwise, do not change
							modifiedLine.append(c);
						}
					}

					//put char together into one single string, and print out
//					System.out.println(modifiedLine.toString());
					
					//write constructed line into file
					writer.write(modifiedLine.toString());
					writer.newLine();           
				}
			}
			reader.close();
			writer.close();
		}
		return true;
	}
}
