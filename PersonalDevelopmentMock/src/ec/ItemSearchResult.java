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
 * Servlet implementation class ItemSearchResult
 */
@WebServlet("/ItemSearchResult")
public class ItemSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//1ページに表示する商品数
	final static int PAGE_MAX_MEAL_COUNT = 8;
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			String searchWord = request.getParameter("search_word");

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchWord", searchWord);

			// 商品リストを取得 ページ表示分のみ
			ArrayList<MealDataBeans> searchResultMealList = MealDAO.getMealsByMealName(searchWord, pageNum, PAGE_MAX_MEAL_COUNT);

			// 検索ワードに対しての総ページ数を取得
			double mealCount = MealDAO.getMealCount(searchWord);
			int pageMax = (int) Math.ceil(mealCount / PAGE_MAX_MEAL_COUNT);

			//総アイテム数
			request.setAttribute("mealCount", (int) mealCount);

			// 総ページ数
			request.setAttribute("pageMax", pageMax);

			// 表示ページ
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mealList", searchResultMealList);

			// 画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/itemsearchresult.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
