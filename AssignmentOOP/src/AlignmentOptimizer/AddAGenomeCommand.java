package AlignmentOptimizer;

public class AddAGenomeCommand implements Command{
	private Alignment alignment;
	public AddAGenomeCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.AddAGenome();		
	}
}
