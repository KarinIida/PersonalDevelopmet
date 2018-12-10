package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.MealDataBeans;

public class BuyDetailDAO {

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return {BuyDataDetailBeans}
	 * @throws SQLException
	 */
	public ArrayList<BuyDetailDataBeans> getBuyDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM buy_detail WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<BuyDetailDataBeans> buyDetailList = new ArrayList<BuyDetailDataBeans>();

			while (rs.next()) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setId(rs.getInt("id"));
				bddb.setBuyId(rs.getInt("buy_id"));
				bddb.setMealId(rs.getInt("meal_id"));
				bddb.setItemId(rs.getInt("item_id"));
				bddb.setNum(rs.getInt("num"));
				buyDetailList.add(bddb);
			}

			System.out.println("searching BuyDataBeansList by BuyID has been completed");
			return buyDetailList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<BuyDataBeans> getBuyDataBeans(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM buy INNER JOIN  delivery"
					+ " ON buy.delivery_id = delivery.id"
					+ " WHERE user_id = ?");

			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<BuyDataBeans> buyDateList = new ArrayList<BuyDataBeans>();

			while (rs.next()) {
				BuyDataBeans bdb = new BuyDataBeans();
				bdb.setId(rs.getInt("id"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setDeliveryMethodName(rs.getString("delivery.name"));
				bdb.setDeliveryMethodPrice(rs.getInt("delivery.price"));
				buyDateList.add(0,bdb);
			}

			System.out.println("searching getBuyDataBeans by BuyID has been completed");
			return buyDateList;
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
	 * 購入詳細登録処理
	 * @param bddb BuyDetailDataBeans
	 * @throws SQLException
	 * 			呼び出し元にスローさせるため
	 */
	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO buy_detail(buy_id,meal_id,item_id,num) VALUES(?,?,?,?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getMealId());
			st.setInt(3, bddb.getItemId());
			st.setInt(4, bddb.getNum());
			st.executeUpdate();
			System.out.println("inserting BuyDetail has been completed");

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
     * 購入IDによる購入詳細情報検索
     * @param buyId
     * @return buyDetailItemList ArrayList<ItemDataBeans>
     *             購入詳細情報のデータを持つJavaBeansのリスト
     * @throws SQLException
     */
	public static ArrayList<MealDataBeans> getMealDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT buy_detail.meal_id, buy_detail.num, meal.id, meal.name, meal.price"
					+ " FROM buy_detail JOIN meal ON buy_detail.meal_id = meal.id WHERE buy_detail.buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<MealDataBeans> buyDetailMealList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans mdb = new MealDataBeans();
				mdb.setId(rs.getInt("id"));
				mdb.setName(rs.getString("name"));
				mdb.setPrice(rs.getInt("price"));
				mdb.setNum(rs.getInt("num"));
				buyDetailMealList.add(mdb);
			}

			System.out.println("searching MealDataBeansList by BuyID has been completed");
			return buyDetailMealList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<MealDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT buy_detail.item_id, buy_detail.num, item.id, item.name, item.price"
					+ " FROM buy_detail JOIN item ON buy_detail.item_id = item.id WHERE buy_detail.buy_id = ?");

			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<MealDataBeans> buyDetailItemList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans idb = new MealDataBeans();
				idb.setItemId(rs.getInt("id"));
				idb.setItemName(rs.getString("name"));
				idb.setItemPrice(rs.getInt("price"));
				idb.setNum(rs.getInt("num"));
				buyDetailItemList.add(idb);
			}

			System.out.println("searching ItemDataBeansList by BuyID has been completed");
			return buyDetailItemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	public static ArrayList<MealDataBeans> BuyMealDateDetail(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT buy_detail.num, meal.id, meal.name, meal.price"
					+ " FROM buy_detail INNER JOIN meal ON buy_detail.meal_id = meal.id WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<MealDataBeans> BuyMealDateDetailList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans mdb = new MealDataBeans();
					mdb.setNum(rs.getInt("num"));
					mdb.setId(rs.getInt("id"));
					mdb.setName(rs.getString("name"));
					mdb.setPrice(rs.getInt("price"));
					BuyMealDateDetailList.add(mdb);
			}

			System.out.println("searching BuyMealDateDetailList by BuyID has been completed");
			return BuyMealDateDetailList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<MealDataBeans> BuyItemDateDetail(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT buy_detail.num, item.id, item.name, item.price"
					+ " FROM buy_detail INNER JOIN item ON buy_detail.item_id = item.id WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<MealDataBeans> BuyItemDateDetailList = new ArrayList<MealDataBeans>();

			while (rs.next()) {
				MealDataBeans idb = new MealDataBeans();
					idb.setNum(rs.getInt("num"));
					idb.setItemId(rs.getInt("id"));
					idb.setItemName(rs.getString("name"));
					idb.setItemPrice(rs.getInt("price"));
				BuyItemDateDetailList.add(idb);
			}

			System.out.println("searching BuyItemDateDetailList by BuyID has been completed");
			return BuyItemDateDetailList;
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
