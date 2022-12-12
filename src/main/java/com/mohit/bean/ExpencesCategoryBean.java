package com.mohit.bean;

public class ExpencesCategoryBean {
	private int exp_catId;
	private String exp_catName;
	private String exp_catDetails;
	private int userId;
	
	public ExpencesCategoryBean() {
		
	}

	public ExpencesCategoryBean(int exp_catId, String exp_catName, String exp_catDetails, int userId) {
		super();
		this.exp_catId = exp_catId;
		this.exp_catName = exp_catName;
		this.exp_catDetails = exp_catDetails;
		this.userId = userId;
	}

	public int getExp_catId() {
		return exp_catId;
	}

	public void setExp_catId(int exp_catId) {
		this.exp_catId = exp_catId;
	}

	public String getExp_catName() {
		return exp_catName;
	}

	public void setExp_catName(String exp_catName) {
		this.exp_catName = exp_catName;
	}

	public String getExp_catDetails() {
		return exp_catDetails;
	}

	public void setExp_catDetails(String exp_catDetails) {
		this.exp_catDetails = exp_catDetails;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ExpensesCategoryBean [exp_catId=" + exp_catId + ", exp_catName=" + exp_catName + ", exp_catDetails="
				+ exp_catDetails + ", userId=" + userId + "]";
	}
	
	
}
