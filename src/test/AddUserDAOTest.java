//AddUserDAOのテストを行うtester


package test;

import dao.AddUserDAO;
import entity.SignIn;

public class AddUserDAOTest {
	public static void main(String[] args) {
		testAdd1();  //ユーザーが存在した場合


	}
	public static void testAdd1() {
		SignIn signIn = new SignIn("test", "12345");
		AddUserDAO dao = new AddUserDAO();
		boolean result = dao.addUser(signIn);
		if(result) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
