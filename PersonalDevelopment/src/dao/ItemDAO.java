package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;
import beans.MealDataBeans;

public class ItemDAO {

	/**
	 * 商品IDによる材料検索
	 * @param mealId
	 * @return MealDataBeans
	 * 				材料のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static MealDataBeans getMealDataBeansByItemId(int mealId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT  meal.id,"
							+ " item.id,"
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

			MealDataBeans mdb = new MealDataBeans();
			if (rs.next()) {
				mdb.setId(rs.getInt("id"));
				mdb.setItemId(rs.getInt("itemid"));
				mdb.setItemName(rs.getString("itemname"));
				mdb.setItemPrice(rs.getInt("itemprice"));
				mdb.setItemTeam(rs.getInt("itemteam"));
				mdb.setItemNum(rs.getString("itemnum"));
			}

			System.out.println("searching MealDataBeans by itemID has been completed");

			return mdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static MealDataBeans ItemId(int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT item.id,"
					+ " item.name,"
					+ " item.price,"
					+ " item.team,"
					+ " item.num,"
					+ " meal.id,"
					+ " meal.name meal_name,"
					+ " meal.file_name"
					+ " FROM item"
					+ " JOIN meal"
					+ " ON item.team = meal.id"
					+ " WHERE item.id = ?");
			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();

			MealDataBeans mdb = new MealDataBeans();
			if (rs.next()) {
				mdb.setItemId(rs.getInt("id"));
				mdb.setItemName(rs.getString("name"));
				mdb.setItemPrice(rs.getInt("price"));
				mdb.setItemTeam(rs.getInt("team"));
				mdb.setItemNum(rs.getString("num"));
				mdb.setId(rs.getInt("id"));
				mdb.setName(rs.getString("meal_name"));
				mdb.setFileName(rs.getString("file_name"));
			}
			System.out.println("searching MealDataBeans by itemID has been completed");
			return mdb;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}

