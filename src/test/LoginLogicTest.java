//LoginLogicのテストを行うtester

package test;

import entity.Account;
import entity.Login;
import model.LoginLogic;


public class LoginLogicTest {
	public static void main (String[] args) {
		testExecute1();  //ログイン成功のテスト
		testExecute2();  //ログイン失敗のテスト
	}

	public static void testExecute1() {
		//form入力値を想定
		Login login = new Login("admin", "1234");
		LoginLogic bo = new LoginLogic();
		Account account = bo.execute(login);

		if(account != null) {
			System.out.println("test1:成功しました");
		} else {
			System.out.println("test1:失敗しました");
		}
	}

	public static void testExecute2() {
		Login login = new Login("admin", "12345");
		LoginLogic bo = new LoginLogic();
		Account account = bo.execute(login);

		if(account == null ) {
			System.out.println("test2:成功しました");
		} else {
			System.out.println("test2:失敗しました");
		}
	}
}
