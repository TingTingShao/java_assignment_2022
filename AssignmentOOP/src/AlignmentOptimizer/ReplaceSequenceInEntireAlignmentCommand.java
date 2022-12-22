package AlignmentOptimizer;

public class ReplaceSequenceInEntireAlignmentCommand implements Command{
	private Alignment alignment;
	public ReplaceSequenceInEntireAlignmentCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.ReplaceSequenceInEntireAlignment();		
	}
}
