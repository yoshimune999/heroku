package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Account;
import entity.AddBudget;
import entity.AddItem;
import entity.ExpenceTotal;
import entity.History;
import entity.ThisMonthTotal;


public class MainDAO {
	//DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/houseHold";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";


	Calendar cal = Calendar.getInstance();

	int y =  cal.get(Calendar.YEAR);
	int m =  cal.get(Calendar.MONTH);
	int day = 0;

	public List<History> getHistory(Account account) {
		List<History> historyList = new ArrayList<>();


		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT 日付 AS date, EXPENCE.費目 AS expence, 入金額 AS dAmount, 出金額 AS wAmount, メモ AS memo FROM HOUSEHOLD "
						+ "JOIN EXPENCE "
						+ "ON HOUSEHOLD.費目ID = EXPENCE.ID AND HOUSEHOLD.USERID = ? "
						+ "JOIN BOP "
						+ "ON HOUSEHOLD.収支ID = BOP.ID "
						+ "ORDER BY HOUSEHOLD.ID DESC "
						+ "OFFSET 0 ROWS "
						+ "FETCH NEXT 5 ROWS ONLY ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をListに保管
			while (rs.next()) {
				String date = rs.getString("date");
				String expence = rs.getString("expence");
				int dAmount = rs.getInt("dAmount");
				int wAmount = rs.getInt("wAmount");
				String memo = rs.getString("memo");
				History history = new History(date, expence, dAmount, wAmount, memo);
				historyList.add(history);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

			return historyList;
	}

	public List<ExpenceTotal> getExpenceTotal(Account account) {
		List<ExpenceTotal> expenceTotalList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT 費目ID AS expenceId, EXPENCE.費目 AS expence, SUM(入金額) AS dAmountTotal, SUM(出金額) AS wAmountTotal FROM HOUSEHOLD "
						+ "JOIN EXPENCE "
						+ "ON HOUSEHOLD.費目ID = EXPENCE.ID AND HOUSEHOLD.USERID = ? AND 日付 >= TO_DATE(1,?,?) AND 日付 <= CURRENT_DATE "
						+ "GROUP BY 費目ID ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());
			pStmt.setInt(2, m +1);
			pStmt.setInt(3, y);

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をListに保管
			while (rs.next()) {
				int expenceId = rs.getInt("expenceId");
				String expence = rs.getString("expence");
				int dAmountTotal = rs.getInt("dAmountTotal");
				int wAmountTotal = rs.getInt("wAmountTotal");
				ExpenceTotal expenceTotal = new ExpenceTotal(expenceId, expence, dAmountTotal, wAmountTotal);
				expenceTotalList.add(expenceTotal);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

		return expenceTotalList;
	}

	public List<ExpenceTotal> getSecondExpenceTotal(Account account) {
		List<ExpenceTotal> secondExpenceTotalList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT 費目ID AS expenceId, EXPENCE.費目 AS expence, SUM(入金額) AS dAmountTotal, SUM(出金額) AS wAmountTotal FROM HOUSEHOLD "
						+ "JOIN EXPENCE "
						+ "ON HOUSEHOLD.費目ID = EXPENCE.ID AND HOUSEHOLD.USERID = ? AND 日付 >= ? AND 日付 <= ? "
						+ "GROUP BY 費目ID ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());

				if (m == 0) {
					pStmt.setDate(2, Date.valueOf(y-1+"-12-01"));
					cal.set(y-1, 12, 1);
					day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					pStmt.setDate(3, Date.valueOf(y-1+"-12-"+day));
				} else {
					pStmt.setDate(2, Date.valueOf(y+"-"+m+"-01"));
					cal.set(y, m -1, 1);
					day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					pStmt.setDate(3, Date.valueOf(y+"-"+m+"-"+day));
				}


			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をListに保管
			while (rs.next()) {
				int expenceId = rs.getInt("expenceId");
				String expence = rs.getString("expence");
				int dAmountTotal = rs.getInt("dAmountTotal");
				int wAmountTotal = rs.getInt("wAmountTotal");
				ExpenceTotal expenceTotal = new ExpenceTotal(expenceId, expence, dAmountTotal, wAmountTotal);
				secondExpenceTotalList.add(expenceTotal);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

		return secondExpenceTotalList;
	}

	public List<ExpenceTotal> getThirdExpenceTotal(Account account) {
		List<ExpenceTotal> thirdExpenceTotalList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT 費目ID AS expenceId, EXPENCE.費目 AS expence, SUM(入金額) AS dAmountTotal, SUM(出金額) AS wAmountTotal FROM HOUSEHOLD "
						+ "JOIN EXPENCE "
						+ "ON HOUSEHOLD.費目ID = EXPENCE.ID AND HOUSEHOLD.USERID = ? AND 日付 >= ? AND 日付 <= ? "
						+ "GROUP BY 費目ID ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());
			if (m == 0) {
				pStmt.setDate(2, Date.valueOf(y-1+"-11-01"));
				cal.set(y-1, 12, 1);
				day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				pStmt.setDate(3, Date.valueOf(y-1+"-11-"+day));
			} else if (m == 1){
				pStmt.setDate(2, Date.valueOf(y-1+"-12-01"));
				cal.set(y-1, 12, 1);
				day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				pStmt.setDate(3, Date.valueOf(y-1+"-12-"+day));
			} else {
				pStmt.setDate(2, Date.valueOf(y+"-"+(m-1)+"-01"));
				cal.set(y , m -2, 1);
				day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				pStmt.setDate(3, Date.valueOf(y+"-"+(m-1)+"-"+day));

			}

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をListに保管
			while (rs.next()) {
				int expenceId = rs.getInt("expenceId");
				String expence = rs.getString("expence");
				int dAmountTotal = rs.getInt("dAmountTotal");
				int wAmountTotal = rs.getInt("wAmountTotal");
				ExpenceTotal expenceTotal = new ExpenceTotal(expenceId, expence, dAmountTotal, wAmountTotal);
				thirdExpenceTotalList.add(expenceTotal);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

		return thirdExpenceTotalList;
	}

	public ThisMonthTotal getThisMonthTotal(Account account) {


		ThisMonthTotal monthTotal = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT SUM(入金額) - SUM(出金額) AS total FROM HOUSEHOLD "
						+ "WHERE 日付 >= TO_DATE(?,?,01) AND 日付 <= CURRENT_DATE AND USERID = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,y);
			pStmt.setInt(2, m);
			pStmt.setInt(3, account.getId());

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をThisMonthTotalに保管
			if (rs.next()) {
				int total = rs.getInt("total");
				monthTotal = new ThisMonthTotal(total);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

		return monthTotal;

	}


	public Map<Integer,AddItem> getAddItem(Account account) {
		Map<Integer,AddItem> expenceMap = new HashMap<Integer,AddItem>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT * FROM EXPENCE WHERE USERID = ?  ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をThisMonthTotalに保管
			while (rs.next()) {
				int expenceId = rs.getInt("ID");
				int userId = rs.getInt("USERID");
				int balanceOPId = rs.getInt("収支ID");
				String expenceName = rs.getString("費目");
				AddItem addItem = new AddItem(expenceId, balanceOPId, userId, expenceName);
				Integer in = rs.getInt("ID");
				expenceMap.put(in, addItem);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}

		return expenceMap;
	}

	public  Map<Integer,AddBudget> getBudget(Account account) {
		Map<Integer,AddBudget> budgetMap = new HashMap<Integer,AddBudget>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT 費目ID AS expenceId, BUDGET.USERID AS ID, EXPENCE.費目 AS 費目, BUDGET.AMOUNT AS AMOUNT FROM BUDGET "
						+ "JOIN EXPENCE "
						+ "ON BUDGET.費目ID = EXPENCE.ID AND BUDGET.USERID = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, account.getId());

			//SELECT文を実行し、結果表をrsに格納
			ResultSet rs = pStmt.executeQuery();

			//結果をListに保管
			while (rs.next()) {
				int userId = rs.getInt("ID");
				int expenceId = rs.getInt("expenceId");
				String expenceName = rs.getString("費目");
				int amount = rs.getInt("AMOUNT");
				AddBudget addBudget = new AddBudget(userId, expenceId, expenceName, amount);
				budgetMap.put(expenceId,addBudget);
			}

		} catch (SQLException e) {
		e.printStackTrace();
		return null;
		}


		return budgetMap;
	}
}
