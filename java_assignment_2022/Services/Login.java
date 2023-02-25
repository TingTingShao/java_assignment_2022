package Services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Entities.TeamMember;

public class Login {	
	//has a private TeamMember subject teamMember
	private TeamMember teamMember = new TeamMember();
	
	//has a private hashmap variable info
	private HashMap<String, String> info;	
	
	//method login
	public String login() throws IOException {
		
		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("please enter your first name: ");
		String firstName=scan1.nextLine().toLowerCase().strip();//toLowerCase, to make input case insensitive
		System.out.println("please enter your second name: ");
		String secondName=scan1.nextLine().toLowerCase().strip();
		
		//string join
		String name= firstName+secondName;
		
		//create a file to store login info name
		String loginName=("./loginName.txt");
		FileWriter fw1 = new FileWriter(loginName);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		bw1.write(name);
		bw1.close();
		fw1.close();
		
		String position =getPosition(name);
		
		//return position
		return position;
	}
	
	//move authorisation into getPosition method 
	private String getPosition(String nm) throws IOException {
		
		info=teamMember.mapTeamMember();
		
		String position = null;
		if (info.containsKey(nm)) {
			
			//get position(value) from teamMember hashmap
			position = info.get(nm).toLowerCase();
			System.out.println("Welcome " + position + " "+ nm);
		}else {
			System.out.println("permission denied");
		}
		return position;
	}
	
	
	public String getName() throws FileNotFoundException {
		// Create a File object representing the text file
	    File file = new File("./loginName.txt");

	    // Create a Scanner to read the file
	    Scanner scanner = new Scanner(file);

	    // Read the name from the first line of the file
	    String name = scanner.nextLine();

	    // Close the Scanner
	    scanner.close();

	    // return the name
		return name;
		
	}
}
