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

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			ArrayList<MealDataBeans> cart = (ArrayList<MealDataBeans>) session.getAttribute("cart");
			ArrayList<MealDataBeans> itemCart = (ArrayList<MealDataBeans>) session.getAttribute("itemCart");
			//セッションにカートがない場合カートを作成
			if (cart == null) {
				cart = new ArrayList<MealDataBeans>();
				session.setAttribute("cart", cart);
			}
			if (itemCart == null) {
				itemCart = new ArrayList<MealDataBeans>();
				session.setAttribute("itemCart", itemCart);
			}

			String cartActionMessage = "";
			//カートに商品が入っていないなら
			if(cart.size() == 0 || itemCart.size() == 0) {
				cartActionMessage = "カートに商品がありません。";
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
