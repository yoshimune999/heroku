//ユーザーが既に存在しないかチェックするDAO
//いなければAddDAOを呼び出す


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.SignIn;

public class SignInDAO {
	//DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/houseHold";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

		public boolean findByUser(SignIn signIn) {

			//DBへ接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				//SELECT文を準備
				String sql = "SELECT NAME,PASS FROM USER WHERE NAME = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, signIn.getUserName());

				//SELECT文を実行し、結果表をrsに格納
				ResultSet rs = pStmt.executeQuery();


				//一致したユーザーがいない場合はNAME,PASSをDBに保存
				if (!rs.next()) {
					//addDAOを呼び出し保存
					AddUserDAO dao = new AddUserDAO();
					boolean result = dao.addUser(signIn);

					return result;

				} else {
					//一致したユーザーがいた場合はfalseを返す
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
}
