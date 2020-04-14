//SignInLogicのテストを行うtester


package test;

import entity.SignIn;
import model.SignInLogic;

public class SignInLogicTest {

	public static void main(String[] args) {
		test1();
		test2();
	}

	public static void test1() {
		SignIn signIn = new SignIn("test", "12345");
		SignInLogic bo = new SignInLogic();
		boolean result = bo.execute(signIn);

		if(result) {
			System.out.println("test:1 成功しました");
		} else {
			System.out.println("test:1 失敗しました");
		}
	}

	public static void test2() {
		SignIn signIn = new SignIn("admin", "1234");
		SignInLogic bo = new SignInLogic();
		boolean result = bo.execute(signIn);

		if(result) {
			System.out.println("test:2 失敗しました");
		} else {
			System.out.println("test:2 成功しました");
		}
	}
}
