package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.AddItem;

public class AddItemDAO {
	//DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/houseHold";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean addItem(AddItem addItem) {

		//DBへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//INSERT文を準備
			String sql = "INSERT INTO HOUSEHOLD (USERID ,日付 ,収支ID ,費目ID ,入金額 ,出金額 ,メモ)  "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, addItem.getUserId());
			pStmt.setString(2, addItem.getDate());
			pStmt.setInt(3, addItem.getBalanceOPId());
			pStmt.setInt(4, addItem.getExpenceId());
			pStmt.setInt(5, addItem.getDAmount());
			pStmt.setInt(6, addItem.getWAmount());
			pStmt.setString(7, addItem.getMemo());

			//INSERT文を実行し、結果表をrsに格納
			 int rs = pStmt.executeUpdate();

			 if(rs == 1) {   //追加 ok
				 return true;


			 } else {   //追加 error
					 return false;

			 }



		} catch (SQLException e) {
				e.printStackTrace();
		}		return false;
	}
}

