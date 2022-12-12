package com.mohit.bean;

public class UsersBean {
	private int userid;
	private String userName;
	private String password;
	private String name;
	private String address;
	private String mobile;
	private String email;
	
	public UsersBean() {
		
	}

	public UsersBean(String userName, String password, String name, String address, String mobile, String email) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	
	public UsersBean(UsersBean source){
		this.userid = source.getUserid();
		this.userName = source.getUserName();
		this.password = source.getPassword();
		this.name = source.getName();
		this.address = source.getAddress();
		this.mobile = source.getMobile();
		this.email = source.getEmail();
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsersBean [userid=" + userid + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", address=" + address + ", mobile=" + mobile + ", email=" + email + "]";
	}
	
	
}
