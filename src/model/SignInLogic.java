//signIn処理を行うBO


package model;

import dao.SignInDAO;
import entity.SignIn;

public class SignInLogic {

	public boolean execute(SignIn signIn) {
		SignInDAO dao = new SignInDAO();
		boolean b = dao.findByUser(signIn);
		return b;
	}
}
