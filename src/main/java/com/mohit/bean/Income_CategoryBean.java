package com.mohit.bean;

public class Income_CategoryBean {
	private int inc_CatId;
	private String inc_CatName;
	private String inc_CatDetails;
	private int userid;
	
	public Income_CategoryBean() {
		
	}

	public Income_CategoryBean(int inc_CatId, String inc_CatName, String inc_CatDetails, int userid) {
		this.inc_CatId = inc_CatId;
		this.inc_CatName = inc_CatName;
		this.inc_CatDetails = inc_CatDetails;
		this.userid = userid;
	}

	public int getInc_CatId() {
		return inc_CatId;
	}

	public void setInc_CatId(int inc_CatId) {
		this.inc_CatId = inc_CatId;
	}

	public String getInc_CatName() {
		return inc_CatName;
	}

	public void setInc_CatName(String inc_CatName) {
		this.inc_CatName = inc_CatName;
	}

	public String getInc_CatDetails() {
		return inc_CatDetails;
	}

	public void setInc_CatDetails(String inc_CatDetails) {
		this.inc_CatDetails = inc_CatDetails;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Income_CategoryBean [inc_CatId=" + inc_CatId + ", inc_CatName=" + inc_CatName + ", inc_CatDetails="
				+ inc_CatDetails + ", userid=" + userid + "]";
	}
	
	
}
