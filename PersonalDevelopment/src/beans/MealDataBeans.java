package beans;

import java.io.Serializable;

public class MealDataBeans implements Serializable {
	private int id;
	private String name;
	private int price;
	private String fileName;
	private int mealBalance;

	private int itemId;
	private String itemName;
	private int itemPrice;
	private int itemTeam;
	private String itemNum;

	private int num;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int mealPrice) {
		this.price = mealPrice;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getMealBalance() {
		return mealBalance;
	}
	public void setMealBalance(int mealBalance) {
		this.mealBalance = mealBalance;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemTeam() {
		return itemTeam;
	}
	public void setItemTeam(int itemTeam) {
		this.itemTeam = itemTeam;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
