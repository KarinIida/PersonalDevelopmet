package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MealDataBeans;
import dao.MealDAO;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

		//商品情報を取得
		ArrayList<MealDataBeans>mealList = MealDAO.getRandMeal(4);

		//リクエストスコープにセット
		request.setAttribute("mealList", mealList);

		//セッションにsearchWordが入っていたら破棄する
		String searchWord = (String)session.getAttribute("searchWord");
		if(searchWord != null) {
			session.removeAttribute("searchWord");
		}

		// 画面表示
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);

		//エラー
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
