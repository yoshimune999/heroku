//SignInDAOをテストするtester

package test;

import dao.SignInDAO;
import entity.SignIn;

public class SignInDAOTest {

	public static void main(String[] args) {
		testSignIn1();  //ユーザーがすでに存在した場合
		testSignIn2();  //ユーザーが存在しなかった場合

	}
	public static void testSignIn1() {
		SignIn signIn = new SignIn("admin", "1234");
		SignInDAO dao = new SignInDAO();
		boolean result = dao.findByUser(signIn);
		if(result) {
			System.out.println("test1: 失敗しました");
		} else {
			System.out.println("test1: 成功しました");
		}
	}

	public static void testSignIn2() {
		SignIn signIn = new SignIn("adminn", "12345");
		SignInDAO dao = new SignInDAO();
		boolean result = dao.findByUser(signIn);
		if(result) {
			System.out.println("test2: 成功しました");
		} else {
			System.out.println("test2: 失敗しました");
		}
	}


}
