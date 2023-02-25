package Controllers;

import java.io.IOException;

import Entities.Document;


public class MergeReportCommand implements Command{
	
	//has a private Document object document
	private Document document;
	
	//constructor
	public MergeReportCommand(Document document) {
		this.document=document;
	}
	@Override
	public void excute() {
		
		//call mergeReport method declared in document
		try {
			document.mergeReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
