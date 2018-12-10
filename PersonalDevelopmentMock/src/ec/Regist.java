package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;

@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//入力内容に誤りがある、確認画面で戻るボタン押し下げでアクセスしてきたときはセッションから入力情報を取得
		UserDataBeans udb = session.getAttribute("udb") != null?(UserDataBeans) Helper.cutSessionAttribute(session, "udb"):null;
		String validationMessage = (String) Helper.cutSessionAttribute(session, "validationMessage");

		//リクエストスコープにセット
		request.setAttribute("udb", udb);
		request.setAttribute("validationMessage",validationMessage);

		// 画面表示
		request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp").forward(request, response);
	}

}
