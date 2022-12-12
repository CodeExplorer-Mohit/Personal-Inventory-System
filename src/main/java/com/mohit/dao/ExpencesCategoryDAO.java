package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.ExpencesCategoryBean;
import com.mohit.utility.ConnectionPool;

public class ExpencesCategoryDAO {
	
	public int addExpencesCategory(ExpencesCategoryBean ecb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into expenses_category(exp_catname, exp_catdetails, userid) values('" + ecb.getExp_catName() + "', '" + ecb.getExp_catDetails() + "', '" + ecb.getUserId()+ "')";
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
	
	
	public int updateExpCat(ExpencesCategoryBean ecb) {
		Connection connect = ConnectionPool.connectDB(); 
		String sql = "update expenses_category set exp_catname='" + ecb.getExp_catName() + "', exp_catdetails='" + ecb.getExp_catDetails() + "' where exp_catid='" + ecb.getExp_catId() +"'";
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
	
	public int deleteExpCat(int exp_catid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "delete from expenses_category where exp_catid='" + exp_catid + "'";
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
	
	
	public ArrayList<ExpencesCategoryBean> findAll(int userId) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from expenses_category where userId='" + userId + "'";
		ArrayList<ExpencesCategoryBean> al = new ArrayList<ExpencesCategoryBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ExpencesCategoryBean ecb = new ExpencesCategoryBean();
				ecb.setExp_catId(rs.getInt("exp_catid"));
				ecb.setExp_catName(rs.getString("exp_catname"));
				ecb.setExp_catDetails(rs.getString("exp_catdetails"));
				ecb.setUserId(rs.getInt("userid"));
				
				al.add(ecb);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	public ExpencesCategoryBean findByExpCatId(int exp_catid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from expenses_category where exp_catid='" + exp_catid + "'";
		ExpencesCategoryBean ecb = new ExpencesCategoryBean();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ecb.setExp_catId(rs.getInt("exp_catid"));
				ecb.setExp_catName(rs.getString("exp_catname"));
				ecb.setExp_catDetails(rs.getString("exp_catdetails"));
				ecb.setUserId(rs.getInt("userid"));
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return ecb;
	}
	
	public int findUserIdByExpCatId(int exp_catid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select userid from expenses_category where exp_catid='" + exp_catid + "'";
		int userId = 0;
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				userId = rs.getInt("userid");
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return userId;
	}
	
	public int findIdByECNameECDetails(String exp_catname, String exp_catdetails){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select exp_catid from expenses_category where exp_catname='" + exp_catname + "' and exp_catdetails='" + exp_catdetails + "'";
		int inc_catid = 0;
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				inc_catid = rs.getInt("exp_catid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return inc_catid;
	}
	
	public int findIdByECName(String exp_catname){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select exp_catid from Expenses_category where exp_catname='" + exp_catname + "'";
		int exp_catid = 0;
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				exp_catid = rs.getInt("exp_catid");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return exp_catid;
	}
	
	public static void main(String args[]) {		
	//	ExpencesCategoryBean ecb = new ExpencesCategoryBean();
//		ecb.setExp_catId(3);
//		ecb.setExp_catName("cash");
//		ecb.setExp_catDetails("from pnb");
//		ecb.setUserId(1004);
		
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO();
//		int r = ecd.updateExpCat(ecb); 
		
//		int r = ecd.addExpencesCategory(ecb);
		
//		int r = ecd.deleteExpCat(3);
		
//		if(r > 0) {
//		System.out.println("Expences_Category Update Success.");
//		}else {
//			System.out.println("Expences_Category Update Failde.");
		
		
//		ArrayList<ExpencesCategoryBean> al = ecd.findAll(1004);
//		for(ExpencesCategoryBean ecb : al) {
//			System.out.println(ecb);
//		}
		
//		ExpencesCategoryBean ecb = ecd.findByExpCatId(1);
//		System.out.println(ecb);
		
//		System.out.println("exp_catid: " + ecd.findIdByECName("check"));
		
//		System.out.println("exp_catid: " + ecd.findIdByECNameECDetails("check", "cybrom"));
//		}
		System.out.println("userId method: " + ecd.findUserIdByExpCatId(25));
	
	}

}
