package AlignmentOptimizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TeamMember {
	public HashMap<String, String> mapTeamMmber() throws IOException{
        // Set the path of the text file
        String file = "updatedTeamMember.txt";     
        // Create a HashMap to store the data from the text file
        HashMap<String, String> teamMember = new HashMap<>();
        
        // Create a BufferedReader object for the text file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        // Read the text file line by line
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into words
            String[] words = line.split(" ");
            
            // Get the first word and use it as the key in the HashMap
            String value = words[0];
            
            // Get the second word and use it as the value in the HashMap
            String key = words[1]+words[2];
            
            // Add the key-value pair to the HashMap
            teamMember.put(key, value);
        }
        
        // Close the BufferedReader object
        reader.close();
        
        // Print the contents of the HashMap
        return teamMember;
    }
}
