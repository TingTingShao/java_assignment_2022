package Controllers;

import java.io.FileNotFoundException;

import Entities.Alignment;

public class RemoveAGenomeCommand implements Command{
	private Alignment alignment;
	public RemoveAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		try {
			alignment.removeAGenome();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

}
