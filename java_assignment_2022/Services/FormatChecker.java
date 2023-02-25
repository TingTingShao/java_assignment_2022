package Services;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

//FormatChecker class is to make sure added data needs to be in correct format, will be used in Alignment class
public class FormatChecker {

	//has a PersonalReportWriter subject rp
	private static PersonalReportWriter rp = new PersonalReportWriter();
	
	Scanner scan = new Scanner(System.in);
	
	//check genome ID format
	public String checkGenomeID() {
//		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the new identifier(start with '>'):  ");
		String newID = scan.nextLine().strip();

		//if doesn't start with ">", need to reenter
		while(!newID.startsWith(">")) {
			System.out.println("wrong genome ID format, please retry");
			newID = scan.nextLine();
		}
		return newID;
	}


	//check Genome content
	public String checkGenome() {
//		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter new genome sequence\n(if the sequence not contain 2500 bases, this genome will not be taken account to calculate the score): ");
		String newSeq= scan.nextLine().toUpperCase().strip();
		while(!newSeq.matches("[ATCG]+")) {

			//make sure the added genome sequence only contains ATCG characters
			System.out.println("sequence contains characters besides ATCG/atcg, retry");
			newSeq = scan.nextLine().toUpperCase();
		}
		return newSeq;

	}

	//check sequence
	public String checkSeq() {
//		Scanner scan = new Scanner(System.in);

		System.out.println("Enter new sequence: ");
		String newSeq= scan.nextLine().toUpperCase().strip();
		while(!newSeq.matches("[ATCG]+")) {

			//make sure added sequence only contains ATCG
			System.out.println("sequence contains characters besides ATCG/atcg, retry");
			newSeq = scan.nextLine().toUpperCase();
		}
		return newSeq;
	}


	public void checkSaveChoice(HashMap<String, String> olderVersion,HashMap<String, String> newVersion) {
//		Scanner scan = new Scanner(System.in);

		System.out.println("Save changes? (Y/N)");
		String input = scan.nextLine().toLowerCase().strip();

		//make sure answer is only choose from YyNn characters
		while(!input.matches("[yn]")) {
			System.out.println("Save changes? (Y/N)");
			input = scan.nextLine();
		}

		if (input.equals("y")) {
			// Save the changes
		} else {		
			// Revert to the original data
			newVersion.clear();
			newVersion.putAll(olderVersion);
		}
	}

	public void checkWriteChoice(HashMap<String, String> data, int score) throws FileNotFoundException {

//		Scanner scan = new Scanner(System.in);
		System.out.println("Write report? (Y/N)");
		String input = scan.nextLine().toLowerCase().strip();

		//make sure answer is only choose from YyNn characters
		while(!input.matches("[yn]")) {
			System.out.println("Write report? (Y/N)");
			input = scan.nextLine();
		}

		//if answer is yes
		if (input.equals("y")) {

			//write as report 
			rp.writePersonalReport(data, score);
			System.out.println("Report is written and submited");
		}	
		else{
			System.out.println("Continue editing");
		}
	}
}



