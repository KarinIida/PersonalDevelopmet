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

/**
 * Servlet implementation class MealAdd
 */
@WebServlet("/MealAdd")
public class MealAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された商品のIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("meal_id"));
			//対象のアイテム情報を取得
			MealDataBeans meal = MealDAO.getMealByMealID(id);

			//追加した商品を表示するためリクエストパラメーターにセット
			request.setAttribute("meal", meal);

			//カートを取得
			ArrayList<MealDataBeans> cart = (ArrayList<MealDataBeans>) session.getAttribute("cart");

			//セッションにカートがない場合カートを作成
			if (cart == null) {
				cart = new ArrayList<MealDataBeans>();
			}

			meal.setNum(1);

			//カートに商品を追加。
			cart.add(meal);

			//カート情報更新
			session.setAttribute("cart", cart);
			request.setAttribute("cartActionMessage", "商品を追加しました。");
			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}