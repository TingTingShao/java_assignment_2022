package AlignmentOptimizer;

public class ViewStartingAlignmentCommand implements Command {
		private Alignment alignment;
		public ViewStartingAlignmentCommand(Alignment alignment) {
			this.alignment=alignment;
		}
		@Override
		public void excute() {
			alignment.viewData();		
		}
		
}
