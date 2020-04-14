package test;

import dao.AddBudgetDAO;
import entity.AddBudget;

public class AddBudgetDAOTest {
	public static void main(String[] args) {
		testAdd1();  //ユーザーが存在した場合
	}


	public static void testAdd1() {
		AddBudget addBudget = new AddBudget(4, 1, 40000);
		AddBudgetDAO dao = new AddBudgetDAO();
		boolean result = dao.addBudget(addBudget);
		if(result) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
