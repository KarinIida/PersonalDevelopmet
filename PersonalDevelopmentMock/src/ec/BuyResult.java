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
import beans.BuyDetailDataBeans;
import beans.MealDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// セッションからカート情報を取得
			ArrayList<MealDataBeans> cart = (ArrayList<MealDataBeans>) Helper.cutSessionAttribute(session, "cart");
			ArrayList<MealDataBeans> itemCart = (ArrayList<MealDataBeans>) Helper.cutSessionAttribute(session, "itemCart");

			BuyDataBeans bdb = (BuyDataBeans) Helper.cutSessionAttribute(session, "bdb");

			// 購入情報を登録
			int buyId = BuyDAO.insertBuy(bdb);
			// 購入詳細情報を購入情報IDに紐づけして登録
			for (MealDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setMealId(cartInItem.getId());
				bddb.setNum(1);
				BuyDetailDAO.insertBuyDetail(bddb);
			}
			if (itemCart != null) {
				for (MealDataBeans cartInItem : itemCart) {
					BuyDetailDataBeans bddb = new BuyDetailDataBeans();
					MealDataBeans mdb = new MealDataBeans();
					bddb.setBuyId(buyId);
					bddb.setItemId(cartInItem.getId());
					bddb.setNum(cartInItem.getNum());
				BuyDetailDAO.insertBuyDetail(bddb);
				}
			}
			/* ====購入完了ページ表示用==== */
			BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("resultBDB", resultBDB);

			//（購入人額ー配達料金）÷単価＝個数
			//(total_price-delivery.price)/item.price
			//SELECT buy.total_price, delivery.price, item.price FROM
			//(( buy INNER JOIN delivery ON buy.delivery_id = delivery.id) INNER JOIN item ON item.id = buy_detail.item_id)
			//WHERE buy_detail.buy_id = ?

			//購入金額と配達料金をとる
			//BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
			//request.setAttribute("resultBDB", resultBDB);

			//単価をとる
			//ArrayList<MealDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			//request.setAttribute("buyIDBList", buyIDBList);

			// 購入アイテム情報
			ArrayList<MealDataBeans> buyMDBList = BuyDetailDAO.getMealDataBeansListByBuyId(buyId);
			request.setAttribute("buyMDBList", buyMDBList);
			ArrayList<MealDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("buyIDBList", buyIDBList);

			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/buyresult.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
