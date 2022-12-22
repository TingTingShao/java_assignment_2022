package AlignmentOptimizer;

import java.util.HashMap;

public class RemoveAGenomeCommand implements Command{
	private Alignment alignment;
	public RemoveAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.RemoveAGenome();		
	}
	

}
