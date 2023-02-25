package Services;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//PersonalReportWriter class provides a method for bioinformaticians to write personal reports
public class PersonalReportWriter {	
	
	public boolean writePersonalReport(HashMap<String, String> data, int score) throws FileNotFoundException {
		
		Login li = new Login();
		
		//convert score into string
		String str = Integer.toString(score);
		String reportName;
		
		//get user name from information saved once logging in 
		reportName = li.getName();
		
		String scoreName;
		String alignmentName;
		
		//write to report name
		alignmentName=(reportName+".alignment.txt");
		scoreName=(reportName+".score.txt");
		
		//write to report path + name
		String outputAlignmentName="./repository/"+alignmentName;
		String outputScoreName="./repository/"+scoreName;
		
		try {
			//writer for alignment and score
			FileWriter fw = new FileWriter(outputAlignmentName);
			FileWriter fw1 = new FileWriter(outputScoreName);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			
			//write hashmap into text file
			for (String key : data.keySet()) {
				bw.write(key + "\n" + data.get(key));
				bw.newLine();
			}
			bw1.write(str);
			bw.close();
			fw.close();
			bw1.close();
			fw1.close();
			return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
















