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
import dao.ItemDAO;

@WebServlet("/ItemAdd")
public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int meal_id = Integer.parseInt(request.getParameter("meal_id"));

			//listをとって
			String[] selectItemIdList = request.getParameterValues("item_id");
			//if null session nageru itemde session errorMessage meal_id
			if (selectItemIdList == null) {
				//sessionに何かをいれておく
				session.setAttribute("validationMessage", "購入商品が入力されていません。");
				//meal_idにredirectして
				response.sendRedirect("Item?meal_id=" + meal_id);
				return;
			}

			//カートを取得
			ArrayList<MealDataBeans> itemCart = (ArrayList<MealDataBeans>) session.getAttribute("itemCart");

			//セッションにカートがない場合カートを作成
			if (itemCart == null) {
				itemCart = new ArrayList<MealDataBeans>();
			}

			if (selectItemIdList != null) {
				for (String selectItem : selectItemIdList) {
					//itemidをdaoに作る
					int itemId = Integer.parseInt(selectItem);
					// itemidをもとにItemをDBからとる
					MealDataBeans item = ItemDAO.ItemId(itemId);
					//if num is null.
					if(request.getParameter("num" + selectItem).isEmpty()) {
						session.setAttribute("validationMessage", "購入量が入力されていません。");
						response.sendRedirect("Item?meal_id=" + meal_id);
						return;
					}
					// 画面で入力した数量
					int num = Integer.parseInt(request.getParameter("num" + selectItem));
					item.setItemNum(item.getItemNum() + " × " + num);
					item.setItemPrice(item.getItemPrice() * num);
					item.setNum(num);
					//カートに商品を追加
					itemCart.add(item);
				}
			}

			//カート情報更新
			session.setAttribute("itemCart", itemCart);
			request.setAttribute("itemCartActionMessage", "材料を追加しました。");
			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}