package test;

import dao.UpdateBudgetDAO;
import entity.AddBudget;

public class UpdateBudgetDAOTest {
	public static void main(String[] args) {
		testAdd1();
	}


	public static void testAdd1() {
		AddBudget addBudget = new AddBudget(4, 1, 50000);
		UpdateBudgetDAO dao = new UpdateBudgetDAO();
		boolean result = dao.upateBudget(addBudget);
		if(result) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
