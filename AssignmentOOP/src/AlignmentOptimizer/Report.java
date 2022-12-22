package AlignmentOptimizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Report {
	
	public void writePersonalReport(HashMap<String, String> data) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter report name: ");
		String reportName=scan.nextLine();
		reportName=(reportName+".alignment.txt").toLowerCase().replaceAll("\\s", "_");
		try {
			FileWriter fw = new FileWriter(reportName);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String key : data.keySet()) {
				bw.write(key + "\n" + data.get(key));
				bw.newLine();
			}
			bw.close();
			fw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mergeReport()throws IOException{
		// Set the name and path of the output file
		File directory = new File("./");
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter merged report name: ");
		String mergeReportName = scan1.nextLine();
		mergeReportName=(mergeReportName+".alignment.txt").toLowerCase().replaceAll("\\s", "_");
		String outputReportName="./repository/"+mergeReportName;
		File outputFile = new File(outputReportName);
		// Create a BufferedWriter to write to the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		// Get a list of all the files in the directory
		File[] files = directory.listFiles();
		// Iterate over the files
		for(File file:files) {
			// Check if the file has the .alignment.txt extension
			if (file.getName().endsWith(".alignment.txt")) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				// Read and write each line from the file to the output file
				String line;
				while((line=reader.readLine())!=null) {
					writer.write(line);
					writer.newLine();
					
				}
				reader.close();
			}
		}writer.close();
	}
}
















