package AlignmentOptimizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PersonalAlignment {
	public HashMap<String, String> alignmentMap(){
		HashMap<String, String> personalAlignmentMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("personalAlignment.fasta"))) {
			String line;
			String key = null;
			StringBuilder value = new StringBuilder();

			while ((line = br.readLine()) != null) {
				if (line.startsWith(">")) {  // identifier line
					if (key != null) {  // store the previous sequence in the HashMap
						personalAlignmentMap.put(key, value.toString());
					}
					key = line;  // set the key for the current sequence
					value = new StringBuilder();  // reset the value for the current sequence
				} else {  // sequence line
					value.append(line);  // append the line to the current sequence
				}
			}

			// store the last sequence in the HashMap
			if (key != null) {
				personalAlignmentMap.put(key, value.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return personalAlignmentMap;
	}
}
