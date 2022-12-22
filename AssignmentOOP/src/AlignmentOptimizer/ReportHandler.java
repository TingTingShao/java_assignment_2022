package AlignmentOptimizer;
import java.io.IOException;


public class ReportHandler {
	public ReportHandler next;
	public ReportHandler(ReportHandler next) {
		this.next=next;
	}
	public void openReport(String fileName) throws IOException {
		if(next!=null) {
			next.openReport(fileName);
		}
	}

}

