package Controllers;

import java.io.FileNotFoundException;

import Entities.Alignment;

public class ReplaceSequenceInEntireAlignmentCommand implements Command{
	private Alignment alignment;
	public ReplaceSequenceInEntireAlignmentCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		try {
			alignment.replaceSequenceInEntireAlignment();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
