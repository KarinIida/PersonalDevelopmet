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
import beans.DeliveryMethodDataBeans;
import beans.MealDataBeans;
import dao.DeliveryDAO;

@WebServlet("/BuyConfirm")
public class BuyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された配送方法IDを取得
			int inputDeliveryMethodId = Integer.parseInt(request.getParameter("delivery_id"));
			//選択されたIDをもとに配送方法Beansを取得
			DeliveryMethodDataBeans userSelectDMB = DeliveryDAO.getDeliveryMethodDataBeansByID(inputDeliveryMethodId);
			//買い物かご
			ArrayList<MealDataBeans> cartIDBList = (ArrayList<MealDataBeans>) session.getAttribute("cart");
			ArrayList<MealDataBeans> itemCartIDBList = (ArrayList<MealDataBeans>) session.getAttribute("itemCart");

			//合計金額
			int totalPrice = Helper.getTotalMealPrice(cartIDBList)
					+ Helper.getTotalItemPrice(itemCartIDBList)
					+ userSelectDMB.getPrice();

			BuyDataBeans bdb = new BuyDataBeans();
			bdb.setUserId((int) session.getAttribute("userId"));
			bdb.setTotalPrice(totalPrice);
			bdb.setDeliveryMethodId(userSelectDMB.getId());
			bdb.setDeliveryMethodName(userSelectDMB.getName());
			bdb.setDeliveryMethodPrice(userSelectDMB.getPrice());

			//購入確定で利用
			session.setAttribute("bdb", bdb);
			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/buyconfirm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
