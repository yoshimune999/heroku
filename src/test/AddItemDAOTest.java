package test;

import dao.AddItemDAO;
import entity.AddItem;

public class AddItemDAOTest {
	public static void main(String[] args) {
		testAdd1();  //ユーザーが存在した場合


	}
	public static void testAdd1() {
		AddItem addItem = new AddItem(4, "2020-02-27", 1, 1, 0, 1000, "ウンチ");
		AddItemDAO dao = new AddItemDAO();
		boolean result = dao.addItem(addItem);
		if(result) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
