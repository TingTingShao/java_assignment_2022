package AlignmentOptimizer;

import java.io.IOException;

public class UploadFileCommand implements Command{
	private Document document;
	public UploadFileCommand(Document document) {
		this.document=document;
	}
	@Override
	public void excute() {
		try {
			document.updateFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
