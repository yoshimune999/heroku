package test;

import dao.AddNewExpenceDAO;
import entity.AddNewExpence;

public class AddNewExpenceDAOTest {
	public static void main(String[] args) {
		testAdd1();  //ユーザーが存在した場合
	}


	public static void testAdd1() {
		AddNewExpence addNewExpence = new AddNewExpence(4, 1, "娯楽費");
		AddNewExpenceDAO dao = new AddNewExpenceDAO();
		boolean result = dao.addExpence(addNewExpence);
		if(result) {
			System.out.println("test1: 成功しました");
		} else {
			System.out.println("test1: 失敗しました");
		}
	}
}
