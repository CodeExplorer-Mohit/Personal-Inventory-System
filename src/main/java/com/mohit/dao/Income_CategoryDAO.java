package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.Income_CategoryBean;
import com.mohit.utility.ConnectionPool;

public class Income_CategoryDAO {
	public int addIncomeCategory(Income_CategoryBean icb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into income_category(inc_catname, inc_catdetails, userid) values('" + icb.getInc_CatName() + "', '" + icb.getInc_CatDetails() + "', '" + icb.getUserid() + "')";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	
	public int updateIncCat(Income_CategoryBean icb) {
		Connection connect = ConnectionPool.connectDB(); 
		String sql = "update income_category set inc_catname='" + icb.getInc_CatName() + "', inc_catdetails='" + icb.getInc_CatDetails() + "' where inc_catid='" + icb.getInc_CatId() +"'";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	public int deleteIncCat(int inc_catid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "delete from income_category where inc_catid='" + inc_catid + "'";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	
	public ArrayList<Income_CategoryBean> findAll(int userId) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from income_category where userId='" + userId + "'";
		ArrayList<Income_CategoryBean> al = new ArrayList<Income_CategoryBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Income_CategoryBean icb = new Income_CategoryBean();
				icb.setInc_CatId(rs.getInt("inc_catid"));
				icb.setInc_CatName(rs.getString("inc_catname"));
				icb.setInc_CatDetails(rs.getString("inc_catdetails"));
				icb.setUserid(rs.getInt("userid"));
				
				al.add(icb);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	public Income_CategoryBean findByIncCatId(int inc_catid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from income_category where inc_catid='" + inc_catid + "'";
		Income_CategoryBean icb = new Income_CategoryBean();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				icb.setInc_CatId(rs.getInt("inc_catid"));
				icb.setInc_CatName(rs.getString("inc_catname"));
				icb.setInc_CatDetails(rs.getString("inc_catdetails"));
				icb.setUserid(rs.getInt("userid"));
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return icb;
	}
	
	public int findIdByICNameICDetails(String inc_catname, String inc_catdetails){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select inc_catid from income_category where inc_catname='" + inc_catname + "' and inc_catdetails='" + inc_catdetails + "'";
		int inc_catid = 0;
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				inc_catid = rs.getInt("inc_catid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return inc_catid;
	}
	
	public int findIdByICName(String inc_catname){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select inc_catid from income_category where inc_catname='" + inc_catname + "'";
		int inc_catid = 0;
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				inc_catid = rs.getInt("inc_catid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return inc_catid;
	}
	
	
	public static void main(String args[]) {		
//		Income_CategoryBean icb = new Income_CategoryBean();
//		icb.setInc_CatId(1);
//		icb.setInc_CatName("check");
//		icb.setInc_CatDetails("Bank SBI");
//		icb.setUserid(1007);
		
//		Income_CategoryDAO icd = new Income_CategoryDAO();
//		int r = icd.updateIncCat(icb); 
		
//		int r = icd.addIncomeCategory(icb);
		
//		int r = icd.deleteIncCat(3);
		
//		if(r > 0) {
//			System.out.println("Income_Category Added Success.");
//		}else {
//			System.out.println("Income_Category Add Failde.");
//		}
		
//		ArrayList<Income_CategoryBean> al = icd.findAll(1006);
//		for(Income_CategoryBean icb : al) {
//			System.out.println(icb);
//		}
		
//		System.out.println("inc_catid: " + icd.findIdByICNameICDetails("check", "Bank SBI"));
//		}
	}
}









