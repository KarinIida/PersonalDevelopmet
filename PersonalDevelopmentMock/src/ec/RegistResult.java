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
 * Servlet implementation class RegistResult
 */
@WebServlet("/RegistResult")
public class RegistResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {
			String inputLoginId = request.getParameter("login_id");
			String inputPassword = request.getParameter("password");
			String inputConfirmPassword = request.getParameter("confirm_password");
			String inputName = request.getParameter("user_name");
			String inputEmail = request.getParameter("user_email");
			Integer inputTel = Integer.valueOf(request.getParameter("user_tel"));
			String inputAddress = request.getParameter("user_address");
			String inputSBirthday = request.getParameter("user_birthday");

			UserDataBeans udb = new UserDataBeans();
			udb.setLoginId(inputLoginId);
			udb.setPassword(inputPassword);
			udb.setName(inputName);
			udb.setEmail(inputEmail);
			udb.setTel(inputTel);
			udb.setAddress(inputAddress);
			udb.setSBirthday(inputSBirthday);

			// 登録が確定されたかどうか確認するための変数
			String confirmed = request.getParameter("confirm_button");

			switch (confirmed) {
			case "cancel":
				session.setAttribute("udb", udb);
				response.sendRedirect("Regist");
				break;

			case "regist":
				UserDAO.insertUser(udb);
				request.setAttribute("udb", udb);

				// 画面表示
				request.getRequestDispatcher("/WEB-INF/jsp/registresult.jsp").forward(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
