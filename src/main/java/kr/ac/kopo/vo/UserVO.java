package kr.ac.kopo.vo;

public class UserVO {

	private String id;
	private String email;
	private String password;
	private String name;
	private String birthDate;
	private String phoneNum;
	private String address;
	private String accountLink;
	private int adminRight;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccountLink() {
		return accountLink;
	}
	public void setAccountLink(String accountLink) {
		this.accountLink = accountLink;
	}
	public int getAdminRight() {
		return adminRight;
	}
	public void setAdminRight(int adminRight) {
		this.adminRight = adminRight;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", birthDate="
				+ birthDate + ", phoneNum=" + phoneNum + ", address=" + address + ", accountLink=" + accountLink
				+ ", adminRight=" + adminRight + "]";
	}
	
	
	
}
