//ユーザーをDBに保存するDAO


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.SignIn;

public class AddUserDAO {
	//DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/houseHold";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean addUser(SignIn signIn) {

		//DBへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//INSERT文を準備
			String sql = "INSERT INTO USER (NAME,PASS) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, signIn.getUserName());
			pStmt.setString(2, signIn.getPass());

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




