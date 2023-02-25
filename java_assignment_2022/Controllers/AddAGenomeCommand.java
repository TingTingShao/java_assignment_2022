package Controllers;

import java.io.FileNotFoundException;

import Entities.Alignment;

public class AddAGenomeCommand implements Command{
	
	//has a Alignment subject alignment
	private Alignment alignment;
	
	//constructor
	public AddAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	
	@Override
	public void excute() {
		
		//call the method in addAGenome declared in alignment
		try {
			alignment.addAGenome();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
