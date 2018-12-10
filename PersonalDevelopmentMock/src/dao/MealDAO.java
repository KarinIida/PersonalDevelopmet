package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.MealDataBeans;

public class MealDAO {

	/**
	 * ランダムで引数指定分のMealDataBeansを取得
	 * @param limit 取得したいかず
	 * @return <MealDataBeans>
	 * @throws SQLException
	 */
	public static ArrayList<MealDataBeans> getRandMeal(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM meal ORDER BY RAND() LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();

			ArrayList<MealDataBeans> mealList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans meal = new MealDataBeans();
				meal.setId(rs.getInt("id"));
				meal.setName(rs.getString("name"));
				meal.setPrice(rs.getInt("price"));
				meal.setFileName(rs.getString("file_name"));
				meal.setMealBalance(rs.getInt("meal_balance"));
				mealList.add(meal);
			}
			System.out.println("getAllMeal completed");
			return mealList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品IDによる商品検索
	 * @param mealId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static MealDataBeans getMealByMealID(int mealId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM meal WHERE id = ?");
			st.setInt(1, mealId);

			ResultSet rs = st.executeQuery();

			MealDataBeans meal = new MealDataBeans();
			if (rs.next()) {
				meal.setId(rs.getInt("id"));
				meal.setName(rs.getString("name"));
				meal.setPrice(rs.getInt("price"));
				meal.setFileName(rs.getString("file_name"));
				meal.setMealBalance(rs.getInt("meal_balance"));
			}
			System.out.println("searching meal by mealID has been completed");
			return meal;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品IDによる材料検索
	 * @param mealId
	 * @return MealDataBeans
	 * 				材料のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static ArrayList<MealDataBeans> getMealDataBeansByMealId(int mealId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT  meal.id,"
							+ " item.id item_id,"
							+ " item.name,"
							+ " item.price,"
							+ " item.team,"
							+ " item.num"
							+ " FROM meal"
							+ " JOIN item"
							+ " ON meal.id = item.team"
							+ " WHERE meal.id = ?");
			st.setInt(1, mealId);

			ResultSet rs = st.executeQuery();

			ArrayList<MealDataBeans> mdbList = new ArrayList<MealDataBeans>();
			while (rs.next()) {
				MealDataBeans mdb = new MealDataBeans();
				mdb.setId(rs.getInt("id"));
				mdb.setItemId(rs.getInt("item_id"));
				mdb.setItemName(rs.getString("name"));
				mdb.setItemPrice(rs.getInt("price"));
				mdb.setItemTeam(rs.getInt("team"));
				mdb.setItemNum(rs.getString("num"));
				mdbList.add(mdb);
			}

			System.out.println("searching ArrayList<MealDataBeans> by mealID has been completed");

			return mdbList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**
	 * 商品検索
	 * @param searchWord
	 * @param pageNum
	 * @param pageMaxMealCount
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<MealDataBeans> getMealsByMealName(String searchWord, int pageNum, int pageMaxMealCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startiMealNum = (pageNum - 1) * pageMaxMealCount;
			con = DBManager.getConnection();

			if (searchWord.length() == 0) {
				// 全検索
				st = con.prepareStatement("SELECT * FROM meal ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startiMealNum);
				st.setInt(2, pageMaxMealCount);
			} else {
				// 商品名検索
				st = con.prepareStatement("SELECT * FROM meal WHERE name LIKE ? ORDER BY id ASC LIMIT ?,? ");
				st.setString(1,"%" + searchWord + "%");
				st.setInt(2, startiMealNum);
				st.setInt(3, pageMaxMealCount);
			}

			ResultSet rs = st.executeQuery();
			ArrayList<MealDataBeans> mealList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans meal = new MealDataBeans();
				meal.setId(rs.getInt("id"));
				meal.setName(rs.getString("name"));
				meal.setPrice(rs.getInt("price"));
				meal.setFileName(rs.getString("file_name"));
				meal.setMealBalance(rs.getInt("meal_balance"));
				mealList.add(meal);
			}
			System.out.println("get Meals by mealName has been completed");
			return mealList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品総数を取得
	 *
	 * @param searchWord
	 * @return
	 * @throws SQLException
	 */
	public static double getMealCount(String searchWord) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from meal where name like ?");
			st.setString(1, "%" + searchWord + "%");
			ResultSet rs = st.executeQuery();
			double coung = 0.0;
			while (rs.next()) {
				coung = Double.parseDouble(rs.getString("cnt"));
			}
			return coung;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
