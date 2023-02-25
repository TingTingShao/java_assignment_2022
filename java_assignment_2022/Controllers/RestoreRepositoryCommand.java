package Controllers;

import Entities.Repository;

public class RestoreRepositoryCommand implements Command{

	private Repository rep = new Repository();
	public  RestoreRepositoryCommand(Repository rep) {
		this.rep=rep;
	}
	@Override
	public void excute() {
		rep.restoreRepository();
	}


}
