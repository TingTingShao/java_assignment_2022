package AlignmentOptimizer;

import java.util.HashMap;

public class SearchGenomeCommand implements Command{
	private Alignment alignment;
	public SearchGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.SearchGenome();		
	}
}
