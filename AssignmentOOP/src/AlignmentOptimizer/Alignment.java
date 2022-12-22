package AlignmentOptimizer;
import java.util.HashMap;
import java.util.Scanner;

public class Alignment {
	private HashMap<String, String> data;
	Report rp = new Report();
	public void viewData() {
		PersonalAlignment personalAlignment = new PersonalAlignment();
		HashMap<String, String> alignment;
		alignment=personalAlignment.alignmentMap();
		data=alignment;
		Scanner scan8 = new Scanner(System.in);
		System.out.println("Do you want to view your personal alignment? (Y/N)");
		String viewChoice = scan8.nextLine();
		if (viewChoice.equalsIgnoreCase("Y")) {
			System.out.println("Starting alignment is: " + "\n"+data);
		}if (viewChoice.equalsIgnoreCase("N")) {
			System.out.println("Personal alignment is not shown");
		}
	}
	
	public void SearchGenome() {	
		Scanner scan = new Scanner(System.in);
		String sequence;
		System.out.println("search genome with sequence: , press E to exit");
		sequence = scan.nextLine();
		// Search the HashMap for the search term
		for (HashMap.Entry<String, String> entry : data.entrySet()) {
			String value = entry.getValue().toLowerCase();
			sequence = sequence.toLowerCase();
			if (value.contains(sequence)) {
				System.out.println(entry.getKey());
			} //else {
			//System.out.println("exit");
			//}
		}
	}

	public void ReplaceAGenome() {
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);
		Alignment alg = new Alignment();
		Scanner scan2=new Scanner(System.in);
		System.out.println("To replace a genome, enter the new identifier: ");
		String identifier = scan2.nextLine();
		System.out.println("To replace a genome, enter coresponding sequence: ");
		String genome=scan2.nextLine();
		System.out.println("To replace a genome, enter target genome identifier: ");
		String targetGenome=scan2.nextLine();
		if (data.containsKey(targetGenome)) {
			data.put(identifier, genome);
			data.remove(targetGenome);
			//System.out.println(data.get(targetGenome));
		}
		alg.CalculateScore(data);
		System.out.println("Save changes? (Y/N)");
		String input = scan2.nextLine();
		if (input.equalsIgnoreCase("Y")) {
			// Save the changes
		} else {
			// Revert to the original data
			data.clear();
			data.putAll(tempData);
		}
		System.out.println("write as report? (Y/N)");
		String writeChoice = scan2.nextLine();
		if (writeChoice.equalsIgnoreCase("Y")) {
			rp.writePersonalReport(data);
		}if(writeChoice.equalsIgnoreCase("N")) {
			System.out.println("Continue editing");
		}
	}
	public void ReplaceSequenceInOneGenome() {
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);
		Alignment alg = new Alignment();
		Scanner scan3 = new Scanner(System.in);
		System.out.println("To replace sequence in one Genome, enter the target identifier: ");
		String targetIdentifier = scan3.nextLine();
		String targetSequence = data.get(targetIdentifier);
		System.out.println("To replace sequence in one Genome, enter target pattern: ");
		String targetPattern = scan3.nextLine();
		System.out.println("To replace sequence in one Genome, enter new pattern: ");
		String newPattern = scan3.nextLine();
		int m = targetPattern.length();
		int n = newPattern.length();
		if (m==n) {
			String newSequence = targetSequence.replace(targetPattern, newPattern);
			data.put(targetIdentifier, newSequence);
			//System.out.println(data.get(targetIdentifier));
		}else {System.out.println("new pattern's length is not the same with the target pattern, abort");}
		alg.CalculateScore(data);
		System.out.println("Save changes? (Y/N)");
		String input = scan3.nextLine();
		if (input.equalsIgnoreCase("Y")) {
			// Save the changes
		} else {
			// Revert to the original data
			data.clear();
			data.putAll(tempData);
		}
		System.out.println("write as report? (Y/N)");
		String writeChoice = scan3.nextLine();
		if (writeChoice.equalsIgnoreCase("Y")) {
			rp.writePersonalReport(data);
		}if(writeChoice.equalsIgnoreCase("N")) {
			System.out.println("Continue editing");
		}
	}
	public void ReplaceSequenceInEntireAlignment() {
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);
		Alignment alg = new Alignment();
		Scanner scan4 = new Scanner(System.in);
		System.out.println("To replace sequence in entire alignment, enter target pattern: ");
		String targetPattern = scan4.nextLine();
		System.out.println("To replace sequence in entire alignment, enter new pattern: ");
		String newPattern = scan4.nextLine();
		newPattern = newPattern.toUpperCase();
		for (HashMap.Entry<String, String> entry : data.entrySet()) {
			String value = entry.getValue();
			String key = entry.getKey();
			if (value.contains(targetPattern)) {
				String sequence = entry.getValue();
				String newSequence = sequence.replace(targetPattern, newPattern);
				data.put(key, newSequence);
			}
			alg.CalculateScore(data);
			System.out.println("Save changes? (Y/N)");
			String input = scan4.nextLine();
			if (input.equalsIgnoreCase("Y")) {
				// Save the changes
			} else {
				// Revert to the original data
				data.clear();
				data.putAll(tempData);
			}
			System.out.println("write as report? (Y/N)");
			String writeChoice = scan4.nextLine();
			if (writeChoice.equalsIgnoreCase("Y")) {
				rp.writePersonalReport(data);
			}if(writeChoice.equalsIgnoreCase("N")) {
				System.out.println("Continue editing");
			}
		}
	}
	public void AddAGenome() {
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);
		Alignment alg = new Alignment();
		Scanner scan5=new Scanner(System.in);
		System.out.println("To add a genome, enter the new identifier: ");
		String identifier = scan5.nextLine();
		System.out.println("To add a genome, enter coresponding sequence: ");
		String genome = scan5.nextLine();
		data.put(identifier, genome);
		alg.CalculateScore(data);
		System.out.println("Save changes? (Y/N)");
		String input = scan5.nextLine();
		if (input.equalsIgnoreCase("Y")) {
			// Save the changes
		} else {
			// Revert to the original data
			data.clear();
			data.putAll(tempData);
		}
		System.out.println("write as report? (Y/N)");
		String writeChoice = scan5.nextLine();
		if (writeChoice.equalsIgnoreCase("Y")) {
			rp.writePersonalReport(data);
		}if(writeChoice.equalsIgnoreCase("N")) {
			System.out.println("Continue editing");
		}
	}
	public void RemoveAGenome() {
		HashMap<String, String> tempData = new HashMap<>();
		tempData.putAll(data);
		Alignment alg = new Alignment();
		Scanner scan6=new Scanner(System.in);
		System.out.println("To remove a genome, enter the target identifier: ");
		String targetIdentifier = scan6.nextLine();
		if (data.containsKey(targetIdentifier)) {
			data.remove(targetIdentifier);
		}
		alg.CalculateScore(data);
		System.out.println("Save changes? (Y/N)");
		String input = scan6.nextLine();
		if (input.equalsIgnoreCase("Y")) {
			// Save the changes
		} else {
			// Revert to the original data
			data.clear();
			data.putAll(tempData);
		}
		System.out.println("write as report? (Y/N)");
		String writeChoice = scan6.nextLine();
		if (writeChoice.equalsIgnoreCase("Y")) {
			rp.writePersonalReport(data);
		}if(writeChoice.equalsIgnoreCase("N")) {
			System.out.println("Continue editing");
		}
	}
	public void CalculateScore(HashMap<String, String> data) {
		System.out.println("calculating the score");
	}
	public void SendAlignment() {
		System.out.println("sending alignment to team leader");
	}
	public void SaveScore() {
		System.out.println("saving score");
	}
	public void SendScore() {
		System.out.println("sending score to team leader");
	}


}
