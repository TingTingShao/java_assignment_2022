package AlignmentOptimizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileHandler extends ReportHandler{

	public TextFileHandler(ReportHandler next) {
		super(next);
	}
	public void openReport(String fileName) throws IOException{
		String reportExtension = fileName.split("\\.")[1];
		if(reportExtension.equals("txt")) {
			BufferedReader reader = new BufferedReader(
					new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("updatedTeamMember.txt"));
			String line;
			while((line=reader.readLine())!=null) {
				writer.write(line);
				writer.newLine();
			}
			reader.close();
			writer.close();
		}else {
			super.openReport(fileName);
		}	
	}


}
