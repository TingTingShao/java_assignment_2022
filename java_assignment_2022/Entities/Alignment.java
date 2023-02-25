package Entities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import Services.FormatChecker;
import Services.ScoreCalculator;

public class Alignment {

	//create an empty hashmap to contain standard alignment
	private HashMap<String, String> data;

	//	has a FormatChecker subject fc
	private static FormatChecker fc = new FormatChecker();

	//has a ScoreCalculator subject sc
	private static ScoreCalculator sc = new ScoreCalculator();
	
	Scanner scan = new Scanner(System.in);

	public void viewAlignment() throws FileNotFoundException, IOException {

		//has a SharedAlignment subject personalAlignment
		SharedAlignment personalAlignment = new SharedAlignment();

		//call personalAlignment method alignmentMap to get the standard alignment, and sign it to this.data
		this.data=personalAlignment.alignmentMap();

		//calculate alignment score
		sc.caculateScore(data);

		System.out.println("View shared SNiP alignment? (Y/N)");
		String viewChoice = scan.nextLine();

		if (viewChoice.equalsIgnoreCase("Y")) {
			System.out.println("Starting alignment is: " + "\n");
			personalAlignment.SNPalignment();

		}
		if (viewChoice.equalsIgnoreCase("N")) {			
		}
	}

	//method: search genome
	public void searchGenome() {

		System.out.println("search genome with sequence: ");
		String sequence = scan.nextLine().toUpperCase();

		//convert hashmap values (all the genome sequence) into list named values
		Collection<String>values = data.values();

		//use this while loop to check if input sequence is included in the data, and if the answer is "E", exit
		while(!values.toString().contains(sequence)&&!sequence.equals("E")) {
			System.out.println("no such sequence in the data, retry");
			sequence = scan.nextLine().toUpperCase();
		}

		// Search the HashMap for the search term
		for (String genomeID : data.keySet()) {
			String genome = data.get(genomeID);

			//use regular expression to match sequence
			if (Pattern.compile(sequence).matcher(genome).find()) {
				System.out.println(genomeID);
			} 
		}
	}

	//method replace a genome
	public void replaceAGenome() throws FileNotFoundException {

		//create a tempData hashmap to contain the data, in order to achieve return function
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);

		System.out.println("To replace a genome");
		
		//new ID to be added into the data has to check if it starts with >
		String newID = fc.checkGenomeID();

		//new genome to be added into the data has to check if it only contains ATCG
		String newGenome = fc.checkGenome();
		
		System.out.println("Enter target genome identifier: ");
		String targetID=scan.nextLine();

		//check if the data contains such genome ID
		while(!data.containsKey(targetID)) {
			System.out.println("traget genome not exist, retry");
			targetID=scan.nextLine();
		}

		//after all the check, put new genome and remove the target genome	
		data.put(newID, newGenome);
		data.remove(targetID);

		//each time after editing, score will be calculated
		int score = sc.caculateScore(data);
		
		//check if answer is y or n, if y, save change, if n, revert the original data
		fc.checkSaveChoice(tempData,data);
			
		//check if the answer is y/n, if it is y, write as report
		fc.checkWriteChoice(data, score);
	}

	//method replace sequence in one genome
	public void replaceSequenceInOneGenome() throws FileNotFoundException {

		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);

		System.out.println("Replace sequence in one genome");
		System.out.println("enter the target identifier: ");
		String targetId = scan.nextLine();

		//check if targetID is in the data
		while(!data.containsKey(targetId)) {
			System.out.println("this identifier does not exist, retry");
			targetId = scan.nextLine();
		}

		//if the genomeID is in the data, get the genome sequence
		String targetGenome = data.get(targetId);

		System.out.println("Choose carefully from the selected genome: ");

		// for user to choose correctly, print out selected genome, since there is no extra check here
		System.out.println(targetId+"\n"+targetGenome);

		System.out.println("enter target sequence: ");
		String targetSeq = scan.nextLine().toUpperCase();
		System.out.println("Make sure new sequence and target sequence has same length, otherwise, this operation will be aborted");

		//new sequence to be added into the data has to be checked if it only contains ATCG
		String newSeq = fc.checkSeq();

		int m = targetSeq.length();
		int n = newSeq.length();

		//check if the length is the same
		if (m==n) {

			//if same, replace targetSeq with newSeq, and put edited sequence back in the data
			String newGenome = targetGenome.replace(targetSeq, newSeq);
			data.put(targetId, newGenome);

		}
		else {
			System.out.println("new pattern's length is not the same with the target pattern, abort");
		}

		int score = sc.caculateScore(data);

		//check if answer is y or n, if y, save change, if n, revert the original data
		fc.checkSaveChoice(tempData,data);

		fc.checkWriteChoice(data, score);
	}

	//method replace sequence in entire alignment
	public void replaceSequenceInEntireAlignment() throws FileNotFoundException {

		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);

		System.out.println("To replace sequence in entire alignment, enter target sequence: ");
		String targetSeq = scan.nextLine().toUpperCase();

		//check if the input sequence is in the data
		while (!data.toString().contains(targetSeq)) {
			System.out.println("no such sequence in the data, retry");
			targetSeq = scan.nextLine().toUpperCase();
		} 

		System.out.println("Make sure new sequence has the same length with target sequence, otherwise, this operation will be aborted!");
		String newSeq = fc.checkSeq();		
		int m = targetSeq.length();
		int n = newSeq.length();
		if(m==n) {
			for(String geneID : data.keySet()) {
				String genome = data.get(geneID);
				String newGenome = genome.replace(targetSeq, newSeq);
				data.put(geneID, newGenome);
			}
		}else {
			System.out.println("length not same, abort");
		}

		int score = sc.caculateScore(data);
		
		fc.checkSaveChoice(tempData,data);

		fc.checkWriteChoice(data, score);
	}

	//method add a genome
	public void addAGenome() throws FileNotFoundException {

		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);

		System.out.println("add a genome");

		//check added ID
		String newID = fc.checkGenomeID();

		//check added genome
		String newGenome=fc.checkGenome();

		//after check, this genome can be added into the data
		data.put(newID, newGenome);
		System.out.println("Genome added!");

		int score = sc.caculateScore(data);
		
		fc.checkSaveChoice(tempData,data);

		fc.checkWriteChoice(data, score);
	}

	//method remove a genome
	public void removeAGenome() throws FileNotFoundException {

		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);

		System.out.println("To remove a genome, enter the target identifier: ");
		String targetId = scan.nextLine();

		//check if the data has such key
		while(!data.containsKey(targetId)) {
			System.out.println("no such genoem ID in the data, retry");
			targetId = scan.nextLine();		
		}

		//if all checks satisfied, remove
		data.remove(targetId);
		System.out.println("Genome removed!");

		int score = sc.caculateScore(data);
		
		fc.checkSaveChoice(tempData,data);

		fc.checkWriteChoice(data, score);
	}

}
