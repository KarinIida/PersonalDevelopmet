package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DeliveryMethodDataBeans;
import beans.MealDataBeans;
import dao.DeliveryDAO;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション
		HttpSession session = request.getSession();
		try {
			Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
			ArrayList<MealDataBeans> cart = (ArrayList<MealDataBeans>) session.getAttribute("cart");
			ArrayList<MealDataBeans> itemCart = (ArrayList<MealDataBeans>) session.getAttribute("itemCart");

			if (!isLogin) {
				// Sessionにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "Buy");
				// Login画面にリダイレクト
				response.sendRedirect("Login");

				//for (MealDataBeans mdb : itemCart) {
					//mdb.getItemPrice()  / mdb.getNum();
				//}

				//MealDataBeans mdb = new MealDataBeans();
				//mdb.setNum(itemCart.getItemPrice() / itemCart.getNum());

			} else if (cart.size() == 0 && itemCart.size() == 0) {
				request.setAttribute("cartActionMessage", "購入する商品がありません");
				// 画面表示
				request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
			} else {
				// 配送方法をDBから取得
				ArrayList<DeliveryMethodDataBeans> dMDBList = DeliveryDAO.getAllDeliveryMethodDataBeans();
				request.setAttribute("dmdbList", dMDBList);
				// 画面表示
				request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
