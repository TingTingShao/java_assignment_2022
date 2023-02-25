package Controllers;

import Entities.Repository;

public class CreateBackupCommand implements Command{
	
	//has a private Repository subject rep
	private Repository rep = new Repository();
	
	//constructor CreateBackupCommand
	public CreateBackupCommand(Repository rep) {
		this.rep=rep;
	}
	
	@Override
	
	public void excute() {
		
		//Execute the createBackup method in rep
		rep.createBackup();
	}

}
