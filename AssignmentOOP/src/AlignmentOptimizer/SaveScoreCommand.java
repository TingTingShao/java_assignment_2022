package AlignmentOptimizer;

public class SaveScoreCommand implements Command{
	private Alignment alignment;
	public SaveScoreCommand(Alignment alignment) {
		this.alignment=alignment;
	}
	@Override
	public void excute() {
		alignment.SaveScore();		
	}

}
