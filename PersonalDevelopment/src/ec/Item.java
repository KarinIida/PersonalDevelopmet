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

@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された商品のIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("meal_id"));

			//対象のアイテム情報を取得
			ArrayList<MealDataBeans>mdbList = MealDAO.getMealDataBeansByMealId(id);

			for (MealDataBeans mealItem : mdbList) {
				MealDataBeans mdb = new MealDataBeans();
				mdb.setItemId(mealItem.getId());
				mdb.setItemName(mealItem.getItemName());
				mdb.setItemPrice(mealItem.getItemPrice());
				mdb.setItemTeam(mealItem.getItemTeam());
				mdb.setItemNum(mealItem.getItemNum());
				mdb.setName(mealItem.getName());
				mdb.setNum(mealItem.getNum());
			}

			//リクエストスコープにセット
			request.setAttribute("mdbList", mdbList);
			request.setAttribute("meal_id", id);

			//ItemAddのsessionの何かを定義して
			Object validationMessage = session.getAttribute("validationMessage");

			//ifでnullじゃなかったらsetで表示してもらう
			if (validationMessage != null) {
				request.setAttribute("validationMessage", validationMessage);
				//removeでsessionから出てもらうまた来世で
				session.removeAttribute("validationMessage");
			}

			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/item.jsp").forward(request, response);


		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
