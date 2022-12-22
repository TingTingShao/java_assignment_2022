package AlignmentOptimizer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static Alignment alignment = new Alignment();
	public static HashMap<String, String> info;	
	public static Document document = new Document();
	public static TeamMember teamMember = new TeamMember();
	public static void main(String[] args) throws IOException {	
		info=teamMember.mapTeamMmber();
		String firstName;
		String secondName;
		String position;
		Scanner scan1 = new Scanner(System.in);
		System.out.println("please enter your first name: ");
		firstName=scan1.nextLine();
		System.out.println("please enter your second name: ");
		secondName=scan1.nextLine();
		String name= firstName+secondName;
		System.out.println("Please enter your position: ");
		position=scan1.nextLine();
		for (HashMap.Entry<String, String> entry : info.entrySet()) {
			String keys = entry.getKey().toLowerCase();
			String values=entry.getValue().toLowerCase();
			position=position.toLowerCase();
			name = name.toLowerCase();
			if (keys.matches(name)&&values.matches(position)) {
				System.out.println("welcome " + position + firstName +" "+ secondName);
				if(position.equals("teamlead")) {
					Button uploadFile = new Button("Updating personal alignment/member information");
					uploadFile.click(new UploadFileCommand(document));
				}
				if(position.equals("bioinformatician")) {
					Button addAGenomeButton = new Button("Add A Genome");
					Button viewStartingAlignmentButton=new Button("view starting alignment");
					Button calculateScoreButton = new Button("Calculate Score");
					Button removeAGenomeButton = new Button("Remove a genome");
					Button replaceAGenomeButton = new Button("Replace a genome");
					Button replaceSequenceInEntireAlignmentButton = new Button("Replace sequence in entire alignment");
					Button replaceSequenceInOneGenomeButton = new Button("Replace sequence in one genome");
					Button searchGenomeButton = new Button("Search genome");
					Button saveScoreButton = new Button("Save score");
					viewStartingAlignmentButton.click(new ViewStartingAlignmentCommand(alignment));
					removeAGenomeButton.click(new RemoveAGenomeCommand(alignment));
					replaceAGenomeButton.click(new ReplaceAGenomeCommand(alignment));
					searchGenomeButton.click(new SearchGenomeCommand(alignment));
				}
			}else {System.out.println("permission denied");}
		}
	}
}
