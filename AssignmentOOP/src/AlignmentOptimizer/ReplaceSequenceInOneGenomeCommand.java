package AlignmentOptimizer;

public class ReplaceSequenceInOneGenomeCommand implements Command{
	private Alignment alignment;
	public ReplaceSequenceInOneGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.ReplaceSequenceInOneGenome();		
	}

}
