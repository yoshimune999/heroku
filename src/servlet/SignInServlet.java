//サインインに関するリクエストを処理するコントローラー

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SignIn;
import model.SignInLogic;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//SignIn.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		if(name.equals("") || pass.equals("")) {
			String em ="入力に誤りがあります";
			request.setAttribute("em",em );
		} else {
		//サインイン処理の実行
		SignIn signIn = new SignIn(name, pass);
		SignInLogic bo = new SignInLogic();
		boolean result = bo.execute(signIn);

			if(result) {
				//リクエストスコープに情報保存
				request.setAttribute("signInUser",signIn );
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signInResult.jsp");
		dispatcher.forward(request, response);
	}

}
