package test;

import java.util.Map;

import dao.MainDAO;
import entity.Account;
import entity.AddItem;

public class MainDAOAddItemTest {
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		Account account = new Account("test", "12345", 4);
		MainDAO dao = new MainDAO();
		Map<Integer,AddItem> expenceMap = dao.getAddItem(account);

		if(expenceMap != null) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
