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

/**
 * Servlet implementation class ItemDelete
 */
@WebServlet("/ItemDelete")
public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			String[] deleteMealIdList = request.getParameterValues("delete_meal_id_list");
			String[] deleteItemIdList = request.getParameterValues("delete_item_id_list");
			ArrayList<MealDataBeans> cart = (ArrayList<MealDataBeans>) session.getAttribute("cart");
			ArrayList<MealDataBeans> itemCart = (ArrayList<MealDataBeans>) session.getAttribute("itemCart");

			String cartActionMessage = "";

			if (deleteMealIdList != null) {
				//削除対象のMealを削除
				for (String deleteMealId : deleteMealIdList) {
					for (MealDataBeans cartInItem : cart) {
						if (cartInItem.getId() == Integer.parseInt(deleteMealId)) {
							cart.remove(cartInItem);
							break;
						}
					}
				}
				cartActionMessage = "食品を削除しました。";
			}
			if (deleteItemIdList != null) {
				//削除対象のItemを削除
				for (String deleteItemId : deleteItemIdList) {
					for (MealDataBeans cartInItem : itemCart) {
						if (cartInItem.getItemId() == Integer.parseInt(deleteItemId)) {
							itemCart.remove(cartInItem);
							break;
						}
					}
				}
				cartActionMessage = "食材を削除しました。";
			} else {
				cartActionMessage = "削除する商品が選択されていません。";
			}
			request.setAttribute("cartActionMessage", cartActionMessage);
			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
