package AlignmentOptimizer;

public class ReplaceAGenomeCommand implements Command{
	private Alignment alignment;
	public ReplaceAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.ReplaceAGenome();
		
	}

}
