package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import base.DBManager;
import beans.BuyDataBeans;
import beans.MealDataBeans;

public class BuyDAO {

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 				購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static BuyDataBeans getBuyDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM buy"
							+ " JOIN delivery"
							+ " ON buy.delivery_id = delivery.id"
							+ " WHERE buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if (rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDeliveryMethodId(rs.getInt("delivery_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));
			}

			System.out.println("searching BuyDataBeans by buyID has been completed");

			return bdb;
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
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO buy(user_id,total_price,delivery_id,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDeliveryMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static MealDataBeans getMealDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT buy_detail.num, meal.id, meal.name, meal.price FROM buy_detail JOIN meal "
					+ "ON buy_detail.meal_id = meal.id WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			MealDataBeans mdb = new MealDataBeans();
			if (rs.next()) {
				mdb.setNum(rs.getInt("num"));
				mdb.setId(rs.getInt("id"));
				mdb.setName(rs.getString("name"));
				mdb.setPrice(rs.getInt("price"));
			}
			System.out.println("searching MealDataBeans Meal by buyID has been completed");
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

	public static MealDataBeans getItemDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT buy_detail.num, item.id, item.name, item.price FROM buy_detail JOIN item "
					+ "ON buy_detail.item_id = item.id WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			MealDataBeans idb = new MealDataBeans();
			if (rs.next()) {
				idb.setNum(rs.getInt("num"));
				idb.setItemId(rs.getInt("id"));
				idb.setItemName(rs.getString("name"));
				idb.setItemPrice(rs.getInt("price"));
			}
			System.out.println("searching MealDataBeans Item by buyID has been completed");
			return idb;
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
