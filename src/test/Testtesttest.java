package test;


import java.util.List;

import dao.MainDAO;
import entity.Account;
import entity.ExpenceTotal;

public class Testtesttest {
	public static void main(String[] args) {
		test1();
		}

		public static void test1() {
			Account account = new Account("test", "12345", 4);
			MainDAO dao = new MainDAO();
			List<ExpenceTotal> expTotal = dao.getSecondExpenceTotal(account);

			if(expTotal != null) {
				System.out.println("test1: 成功しました");
			} else {
				System.out.println("test1: 失敗しました");
			}
		}
}
