package Entities;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Controllers.AddAGenomeCommand;
import Controllers.ClearRepositoryCommand;
import Controllers.CreateBackupCommand;
import Controllers.MergeReportCommand;
import Controllers.RemoveAGenomeCommand;
import Controllers.ReplaceAGenomeCommand;
import Controllers.ReplaceSequenceInEntireAlignmentCommand;
import Controllers.ReplaceSequenceInOneGenomeCommand;
import Controllers.RestoreRepositoryCommand;
import Controllers.SearchGenomeCommand;
import Services.Login;

public class Main {

	// has a Alignment subject alignment, which contains all the methods for
	// bioinformaticians to edit the alignment
	public static Alignment alignment = new Alignment();

	// has a Repository subject repository, which contains all the method for
	// technical support to backup, restore, and clear repository
	public static Repository repository = new Repository();

	// has a Login subject lg, which provides the methods to authorise user login
	public static Login lg = new Login();

	// has a Document subject document, which provides all the methods for teamlead
	// to perform on the reports
	public static Document document = new Document();

	public static void main(String[] args) throws IOException {
		// make the repository folder
		File folder = new File("./repository");
		if (!folder.exists()) {
			folder.mkdir();
		}

		// read the hiv fasta file, and put it into three different files
		// current optimal alignment, corresponding SNiP alignment in the repository, and the sharedAlignment
		document.updateOptimalAlignment("hiv.fasta");
		
		// note! after the teamlead update shared alignment using one of bioinformaticians', this line should be inactive
		//otherwise, the starting optimal alignment is always from hiv.fasta
		document.updateSharedAlignment("hiv.fasta");

		String position;

		// use the name to login, and store the name to a file for later use: name the reports
		//log in name should choose from the team.txt: Jozef Groenewegen, Marc Janssens, Werner Lippens, Yves Colpaert, Jeff Stevenson
		// according name, get position
		position = lg.login();

		// according to different positions, present different ''buttons''
		switch (position) {

		case "bioinformatician":
			
			//view shared alignment
			alignment.viewAlignment();

			// all the options bioinformaticians can choose to click on
			Button addAGenomeButton = new Button("Add A Genome");
			Button removeAGenomeButton = new Button("Remove a genome");
			Button replaceAGenomeButton = new Button("Replace a genome");
			Button replaceSequenceInEntireAlignmentButton = new Button("Replace sequence in entire alignment");
			Button replaceSequenceInOneGenomeButton = new Button("Replace sequence in one genome");
			Button searchGenomeButton = new Button("Search genome");

			// before run, need to first choose the operations first, if you don't want to operate certain part, please inactivate the click line
			searchGenomeButton.click(new SearchGenomeCommand(alignment));
			removeAGenomeButton.click(new RemoveAGenomeCommand(alignment));
			addAGenomeButton.click(new AddAGenomeCommand(alignment));
			replaceAGenomeButton.click(new ReplaceAGenomeCommand(alignment));
			searchGenomeButton.click(new SearchGenomeCommand(alignment));
			replaceSequenceInEntireAlignmentButton.click(new ReplaceSequenceInEntireAlignmentCommand(alignment));
			replaceSequenceInOneGenomeButton.click(new ReplaceSequenceInOneGenomeCommand(alignment));
			searchGenomeButton.click(new SearchGenomeCommand(alignment));
			
			break;

		case "teamlead":

			// merge report
			Button mergeReport = new Button("Merge sumitted reports");
			mergeReport.click(new MergeReportCommand(document));
			
			Scanner scan = new Scanner(System.in);
			
			//update the optimal alignment
			System.out.println("update the optimal alignment with one of the bioinformaticians' alignment(Y/N)?");
			String answer = scan.nextLine().toLowerCase();

			// user choices
			if (answer.equals("y")) {
				System.out.println("input bioinformatician's name(first name + second name)");
				String input = scan.nextLine();

				// to retrieve user name
				input = input.strip().toLowerCase().replaceAll("[^a-z]", "");

				//file name + path
				String fileName = "./repository/"+input+".alignment.txt";

				document.updateOptimalAlignment(fileName);
			}

			// update shared alignment
			System.out.println("update the shared alignment with one of the bioinformaticians' alignment(Y/N)?");
			String answer1 = scan.nextLine().toLowerCase();

			// user choices
			if (answer1.equals("y")) {
				System.out.println("input bioinformatician's name(first name + second name)");
				String input = scan.nextLine();

				// to retrieve user name
				input = input.strip().toLowerCase().replaceAll("[^a-z]", "");
				
				//file name
				String fileName = "./repository/"+input+".alignment.txt";
				
				document.updateSharedAlignment(fileName);
			}

			break;

		case "technicalsupport":

			// after operation options for technical support
			Button backupButton = new Button("back up the repository");
			Button restoreRepositoryButton = new Button("Restore the repository");
			Button clearRepositoryButton = new Button("Clear the repository");

			// perform backup
			backupButton.click(new CreateBackupCommand(repository));

			// print backup date
			System.out.println(repository.getBackupDate());

			// perform restore
			restoreRepositoryButton.click(new RestoreRepositoryCommand(repository));

			// perform clear
			clearRepositoryButton.click(new ClearRepositoryCommand(repository));

			break;
		}
	}

}
