package Controllers;

import Entities.Repository;

public class ClearRepositoryCommand implements Command{
	
	//has a private Repository subject rep
	private Repository rep = new Repository();
	
	//constructor
	public  ClearRepositoryCommand(Repository rep) {
		this.rep=rep;
	}
	
	@Override
	public void excute() {
		
		//Excute the clearRepository method declared in rep
		rep.clearRepository();
	}
}
