package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.MealDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

@WebServlet("/UserBuyHistory")
public class UserBuyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	try {
		int userId = (int) session.getAttribute("userId");
		int buyId = Integer.parseInt(request.getParameter("buy_id"));

		//購入方法
		BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
		request.setAttribute("resultBDB", resultBDB);

		//購入商品(buy_detail.meal_id=meal.id)
		ArrayList<MealDataBeans> buyMDB = BuyDetailDAO.BuyMealDateDetail(buyId);
		request.setAttribute("buyMDB", buyMDB);

		//購入材料(buy_detail.item_id=item.id)
		ArrayList<MealDataBeans> buyIDB = BuyDetailDAO.BuyItemDateDetail(buyId);
		request.setAttribute("buyIDB", buyIDB);

		// 画面表示
		request.getRequestDispatcher("/WEB-INF/jsp/userbuyhistorydetail.jsp").forward(request, response);

		} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("errorMessage", e.toString());
		response.sendRedirect("Error");
		}
	}
}
