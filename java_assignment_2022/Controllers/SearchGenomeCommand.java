package Controllers;

import Entities.Alignment;

public class SearchGenomeCommand implements Command{
	private Alignment alignment;
	public SearchGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.searchGenome();		
	}
}
