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

@WebServlet("/Meal")
public class Meal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			//選択された商品のIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("meal_id"));

			//商品情報を取得
			MealDataBeans meal = MealDAO.getMealByMealID(id);

			//対象のアイテム情報を取得
			ArrayList<MealDataBeans>mdbList = MealDAO.getMealDataBeansByMealId(id);

			//リクエストスコープにセット
			request.setAttribute("meal", meal);
			request.setAttribute("mdbList", mdbList);

			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/meal.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}

