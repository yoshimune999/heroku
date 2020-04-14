//MainDAO getHistoryをテストするtester

package test;

import java.util.List;

import dao.MainDAO;
import entity.Account;
import entity.History;

public class MainDAOHistoryTest {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		Account account = new Account("test", "12345", 4);
		MainDAO dao = new MainDAO();
		List<History> his = dao.getHistory(account);

		if(his != null) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}

}
