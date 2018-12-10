package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserDataUpdateConfirm
 */
@WebServlet("/UserDataUpdateConfirm")
public class UserDataUpdateConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*文字化け対策*/
		request.setCharacterEncoding("UTF-8");

		//セッション開始
		HttpSession session = request.getSession();
		try {
			//エラーメッセージを格納する変数
			String validationMessage = "";

			//入力フォームから受け取った値をBeans
			UserDataBeans udb = new UserDataBeans();
			udb.setUpdateUserDataBeansInfo(request.getParameter("user_name"),
					request.getParameter("login_id"),
					request.getParameter("user_address"),
					(int) session.getAttribute("userId"),
					request.getParameter("email"),
					Integer.valueOf(request.getParameter("tel")),
					request.getParameter("sBirthday"));

			//ログインIDの入力規則チェック 英数字 ハイフン アンダースコアのみ入力可能
			if (!Helper.isLoginIdValidation(udb.getLoginId())) {
				validationMessage = "半角英数とハイフン、アンダースコアのみ入力できます。";
			}
			//loginIdの重複をチェック
			if ( UserDAO.isOverlapLoginId(udb.getLoginId(),(int) session.getAttribute("userId"))) {
				validationMessage = "ほかのユーザーが使用中のログインIDです。";
			}

			//バリデーションエラーメッセージがないなら確認画面へ
			if(validationMessage.length() == 0){

				//確認画面へ
				request.setAttribute("udb",udb);

				// 画面表示
				request.getRequestDispatcher("/WEB-INF/jsp/userdataupdateconfirm.jsp").forward(request, response);
			}else {
				//セッションにエラーメッセージを持たせてユーザー画面へ
				session.setAttribute("validationMessage", validationMessage);
				response.sendRedirect("UserData");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
