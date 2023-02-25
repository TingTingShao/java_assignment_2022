package Entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SharedAlignment {

	public HashMap<String, String> alignmentMap(){
		HashMap<String, String> personalAlignmentMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("sharedAlignment.fasta"))) {
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

	public boolean SNPalignment() throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new 
				FileReader("./sharedAlignment.fasta"))) {
			String line = reader.readLine();
			//reference genome ID
			System.out.println(line);
			//reference genome sequence
			String reference =reader.readLine();
			System.out.println(reference);
			while((line=reader.readLine())!=null){
				if(line.startsWith(">")) {
					System.out.println(line);
				}
				//otherwise, compare the sequence with the reference and print the result
				else {
					StringBuilder modifiedLine = new StringBuilder();
					for(int i = 0; i<line.length(); i++) {
						char c = line.charAt(i);
						if(c == reference.charAt(i)) {
							modifiedLine.append(".");
						}else{
							modifiedLine.append(c);
						}
					}
					System.out.println(modifiedLine.toString());

				}
			}
			reader.close();
		}
		return true;
	}
}

