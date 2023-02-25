package Controllers;

import java.io.FileNotFoundException;

import Entities.Alignment;

public class ReplaceSequenceInOneGenomeCommand implements Command{
	private Alignment alignment;
	public ReplaceSequenceInOneGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		try {
			alignment.replaceSequenceInOneGenome();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
