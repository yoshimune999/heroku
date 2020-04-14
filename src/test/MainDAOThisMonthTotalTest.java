package test;

import dao.MainDAO;
import entity.Account;
import entity.ThisMonthTotal;

public class MainDAOThisMonthTotalTest {
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		Account account = new Account("test", "12345", 4);
		MainDAO dao = new MainDAO();
		ThisMonthTotal monthTotal = dao.getThisMonthTotal(account);

		if(monthTotal != null) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
