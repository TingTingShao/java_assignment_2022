package AlignmentOptimizer;

import java.io.IOException;
import java.util.Scanner;
// add comments on document 
public class Document {
	public void updateFile() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("which file do you want to update? optimal alignmetn or team member?");
		System.out.println("enter file name to upload: ");
		String fileName= scan.nextLine();
		ReportHandler chain=new TextFileHandler(new FastaFileHandler(null));
		chain.openReport(fileName);		
	}
}
