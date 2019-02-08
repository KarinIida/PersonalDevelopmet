package beans;

import java.io.Serializable;
import java.util.Date;

public class UserDataBeans implements Serializable {
	private String name;
	private String address;
	private String loginId;
	private String password;
	private int id;
	private String email;
	private int tel;
	private String sBirthday;
	private Date dBirthday;
	private String createDate;

	public UserDataBeans() {
		this.name = "";
		this.address = "";
		this.loginId = "";
		this.password = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getSBirthday() {
		return sBirthday;
	}

	public void setSBirthday(String sBirthday) {
		this.sBirthday = sBirthday;
	}

	public Date getDBirthday() {
		return dBirthday;
	}

	public void setDBirthday(Date dBirthday) {
		this.dBirthday = dBirthday;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setUpdateUserDataBeansInfo(String name, String loginId, String address, int id, String email, int tel, String sBirthday ) {
		this.name = name;
		this.loginId = loginId;
		this.address = address;
		this.id = id;
		this.email = email;
		this.tel = tel;
		this.sBirthday = sBirthday;
	}
}
