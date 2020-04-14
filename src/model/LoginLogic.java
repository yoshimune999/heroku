//ログイン処理を担当するBO

package model;

import dao.UserDAO;
import entity.Account;
import entity.Login;

public class LoginLogic {
	public Account execute(Login login) {
		UserDAO dao = new UserDAO();
		Account account = dao.findByUser(login);
		return account;
	}
}
