package AlignmentOptimizer;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FastaFileHandler extends ReportHandler{
	public FastaFileHandler(ReportHandler handler) {super(handler);}
	public void openReport(String fileName) throws IOException {
		String reportExtension = fileName.split("\\.")[1];
		if(reportExtension.equals("fasta")) {
			BufferedReader reader = new BufferedReader(
					new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("./repository/optimalAlignment.fasta"));
					
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
