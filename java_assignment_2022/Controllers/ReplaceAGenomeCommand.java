package Controllers;

import java.io.FileNotFoundException;

import Entities.Alignment;

public class ReplaceAGenomeCommand implements Command{
	private Alignment alignment;
	public ReplaceAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		try {
			alignment.replaceAGenome();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
