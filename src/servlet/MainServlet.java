package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddBudgetDAO;
import dao.AddItemDAO;
import dao.AddNewExpenceDAO;
import dao.MainDAO;
import dao.UpdateBudgetDAO;
import entity.Account;
import entity.AddBudget;
import entity.AddItem;
import entity.AddNewExpence;
import entity.ExpenceTotal;
import entity.History;
import entity.ThisMonthTotal;
import model.AddNewExpenceLogic;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン中かチェック
		HttpSession session = request.getSession();
		Account loginAccount = (Account) session.getAttribute("loginAccount") ;

		if (loginAccount != null) {
			//直近5件の履歴を取得
			MainDAO dao = new MainDAO();
			List<History> historyList = dao.getHistory(loginAccount);
			if (historyList != null) {
				//リクエストスコープに保存
				request.setAttribute("historyList", historyList);
			}

			//費目ごとの合計を取得
			List<ExpenceTotal> expenceTotalList = dao.getExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("expenceTotalList", expenceTotalList);
			}
			//先月の費目
			List<ExpenceTotal> secondExpenceTotalList = dao.getSecondExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("secondExpenceTotalList", secondExpenceTotalList);
			}
			//先々月の費目
			List<ExpenceTotal> thirdExpenceTotalList = dao.getThirdExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("thirdExpenceTotalList", thirdExpenceTotalList);
			}

			//費目ごとの予算を取得
			Map<Integer,AddBudget> budgetMap = dao.getBudget(loginAccount);
			if (expenceTotalList != null) {
				//セッションスコープに保存
				session.setAttribute("budgetMap", budgetMap);
			}

			//今月の支出合計を取得
			ThisMonthTotal monthTotal = dao.getThisMonthTotal(loginAccount);
			if (monthTotal != null) {
				//リクエストスコープに保存
				request.setAttribute("thisMonthTotal", monthTotal);
			}

			//費目の項目を取得
			Map<Integer,AddItem> expenceMap = dao.getAddItem(loginAccount);
			if (expenceMap != null) {
				//セッションスコープに保存
				session.setAttribute("expenceMap", expenceMap);
			}

			//メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else {
			//ログイン画面にリダイレクト
			response.sendRedirect("/householdAccountsSystem");
		}

	}













	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン中かチェック
 		HttpSession session = request.getSession();
		Account loginAccount = (Account) session.getAttribute("loginAccount") ;
		//リクエストパラメーターのエンコーディング
		request.setCharacterEncoding("UTF-8");

		//どのフォームからのリクエストか分岐する
		if (loginAccount != null) {
			String addItemForm = request.getParameter("addItemForm");
			String budgetForm = request.getParameter("budgetForm");
			String addNewExpenceForm = request.getParameter("addNewExpenceForm");



			//項目追加フォーム
			if(addItemForm != null) {
				//リクエストパラメーターの取得
				Integer expenceId = Integer.valueOf(request.getParameter("expenceId"));
				String date = request.getParameter("date");
				Integer addItemAmount = Integer.valueOf(request.getParameter("addItemAmount"));
				String memo = request.getParameter("memo");
				AddItem addItem = null;


				//パラメーターのexpenceIdとスコープのmapのkeyを評価して情報を取得する
				Map<Integer,AddItem> expenceMap = (Map<Integer,AddItem>) session.getAttribute("expenceMap");
				AddItem add = expenceMap.get(expenceId);


				//支出なのか収入なのかを評価
				if (add.getBalanceOPId() == 1) {
					//収支IDが１、支出の時
					addItem = new AddItem(add.getUserId(), date, add.getBalanceOPId(), expenceId, 0, addItemAmount, memo);
				} else if (add.getBalanceOPId() == 2) {
					//収支IDが２、収入の時
					addItem = new AddItem(add.getUserId(), date, add.getBalanceOPId(), expenceId, addItemAmount, 0, memo);
				}


				//項目追加機能の実行
				AddItemDAO dao = new AddItemDAO();
				boolean result = dao.addItem(addItem);
				if(!result) {
					//error画面にフォワード
					request.setAttribute("addItemSys", "error");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher.forward(request, response);

				}
			}



			//予算登録フォーム
			if (budgetForm != null) {
				//リクエストパラメーターの取得
				Integer budgetExpenceId = Integer.valueOf(request.getParameter("budgetExpenceId"));
				Integer budgetAmount = Integer.valueOf(request.getParameter("budgetAmount"));

				//情報を格納
				AddBudget addBudget = new AddBudget(loginAccount.getId(), budgetExpenceId, budgetAmount);


				//すでに登録されている項目ではないかチェック
				Map<Integer,AddBudget> budgetMap = (Map<Integer,AddBudget>) session.getAttribute("budgetMap");
				if (budgetMap.get(budgetExpenceId) != null) {
					//すでに登録されていたら更新するDAOを呼ぶ
					UpdateBudgetDAO dao = new UpdateBudgetDAO();
					boolean re = dao.upateBudget(addBudget);
					if(!re) {
						//error画面にフォワード
						request.setAttribute("updateBudgetSys", "error");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
						dispatcher.forward(request, response);
					}

				} else {
					//予算登録機能の実行
					AddBudgetDAO dao = new AddBudgetDAO();
					boolean result = dao.addBudget(addBudget);
					if(!result) {
						//error画面にフォワード
						request.setAttribute("addBudgetSys", "error");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
						dispatcher.forward(request, response);
					}
				}
			}



			//費目追加フォーム
			if(addNewExpenceForm != null) {
				//リクエストパラメーターの取得
				Integer balanceOP = Integer.valueOf(request.getParameter("balanceOP"));
				String newExpence = request.getParameter("newExpence");

				//既に存在する費目でないかチェック
				Map<Integer,AddItem> expenceMap = (Map<Integer,AddItem>) session.getAttribute("expenceMap");
				AddNewExpenceLogic bo = new AddNewExpenceLogic();
				boolean existence = bo.execute(expenceMap, newExpence);

				if(existence) {
						//すでに費目が存在したらerror画面にフォワード
						request.setAttribute("addExpenceSys", "error");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
						dispatcher.forward(request, response);
				}

				//情報を格納
				AddNewExpence addNewExpence = new AddNewExpence(loginAccount.getId(), balanceOP, newExpence);
				//費目追加機能の実行
				AddNewExpenceDAO dao = new AddNewExpenceDAO();
				boolean result = dao.addExpence(addNewExpence);
				if(!result) {
					//error画面にフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher.forward(request, response);
				}
			}


			//直近5件の履歴を取得
			MainDAO dao = new MainDAO();
			List<History> historyList = dao.getHistory(loginAccount);
			if (historyList != null) {
				//リクエストスコープに保存
				request.setAttribute("historyList", historyList);
			}

			//費目ごとの合計を取得
			List<ExpenceTotal> expenceTotalList = dao.getExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("expenceTotalList", expenceTotalList);
			}

			//先月の費目
			List<ExpenceTotal> secondExpenceTotalList = dao.getSecondExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("secondExpenceTotalList", secondExpenceTotalList);
			}
			//先々月の費目
			List<ExpenceTotal> thirdExpenceTotalList = dao.getThirdExpenceTotal(loginAccount);
			if (expenceTotalList != null) {
				//リクエストスコープに保存
				request.setAttribute("thirdExpenceTotalList", thirdExpenceTotalList);
			}

			//費目ごとの予算を取得
			Map<Integer,AddBudget> budgetMap = dao.getBudget(loginAccount);
			if (expenceTotalList != null) {
				//セッションスコープに保存
				session.setAttribute("budgetMap", budgetMap);
			}

			//今月の支出合計を取得
			ThisMonthTotal monthTotal = dao.getThisMonthTotal(loginAccount);
			if (monthTotal != null) {
				//リクエストスコープに保存
				request.setAttribute("thisMonthTotal", monthTotal);
			}

			//費目の項目を取得
			Map<Integer,AddItem> expenceMap = dao.getAddItem(loginAccount);
			if (expenceMap != null) {
				//セッションスコープに保存
				session.setAttribute("expenceMap", expenceMap);
			}

			//メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else {
			//ログイン画面にリダイレクト
			response.sendRedirect("/householdAccountsSystem");
		}
	}

}
