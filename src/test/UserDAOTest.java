//UserDAOのテストを行うtester

package test;

import dao.UserDAO;
import entity.Account;
import entity.Login;

public class UserDAOTest {
	public static void main (String[] args) {
		testFindByUser1();  //userが見つかる場合のテスト
		testFindByUser2();  //userが見つからない場合のテスト
	}

	public static void testFindByUser1() {
		//form入力値を想定
		Login login = new Login("admin", "1234");
		//UserDAOをインスタンス化してfindByUserメソッド実行しloginを渡す
		//resultの型がAccountなのは結果を受け取るAccount Entityを想定しているため（つまりAccountクラスのテスト）
		UserDAO dao = new UserDAO();
		Account result = dao.findByUser(login);

		if(result != null &&
				result.getUserName().equals("admin") &&
				result.getPass().equals("1234")) {
			System.out.println("test1:成功しました");
		} else {
			System.out.println("test1:失敗しました");
		}
	}

	public static void testFindByUser2() {
		Login login = new Login("admin", "12345");
		UserDAO dao = new UserDAO();
		Account result = dao.findByUser(login);

		if(result == null) {
			System.out.println("test1:成功しました");
		} else {
			System.out.println("test1:失敗しました");
		}
	}
}
