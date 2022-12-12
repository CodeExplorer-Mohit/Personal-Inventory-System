package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.ExpencesBean;
import com.mohit.utility.ConnectionPool;

public class ExpencesDAO {
	public int addExpences(ExpencesBean eb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into Expences(exp_ac, userid, exp_catid, amount, transaction_date, payby, remark) values('" + eb.getExp_ac() + "', '" + eb.getUserid() + "', '" + eb.getExp_catid() + "', '" + eb.getAmount() + "', '" + eb.getTransaction_date() + "', '" + eb.getPayby() + "', '" + eb.getRemark() + "')";
		String sql1 = "insert into cash_book(transactiion_date, amount, userid, operation) values('" + eb.getTransaction_date() + "', '" + eb.getAmount() + "', '" + eb.getUserid() + "', 'pay')";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql1);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	
	public int updateExpence(ExpencesBean eb) {
		Connection connect = ConnectionPool.connectDB(); 
		String sql = "update expences set exp_ac='" + eb.getExp_ac() + "', amount='" + eb.getAmount() + "', transaction_date='" + eb.getTransaction_date() + "', payby='" + eb.getPayby() + "', remark='" + eb.getRemark() + "' where exp_id='" + eb.getExp_id() + "'";
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
	
	public int deleteExpence(int exp_id) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "delete from Expences where exp_id='" + exp_id + "'";
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
	
	
	public ArrayList<ExpencesBean> findAll() {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from expences";
		ArrayList<ExpencesBean> al = new ArrayList<ExpencesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ExpencesBean eb = new ExpencesBean();
				eb.setExp_id(rs.getInt("exp_id"));
				eb.setExp_ac(rs.getString("exp_ac"));
				eb.setUserid(rs.getInt("userid"));
				eb.setExp_catid(rs.getInt("exp_catid"));
				eb.setAmount(rs.getDouble("amount"));
				eb.setTransaction_date(rs.getString("transaction_date"));
				eb.setPayby(rs.getString("payby"));
				eb.setRemark(rs.getString("remark"));
				
				al.add(eb);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	public ArrayList<ExpencesBean> findAllByUi(int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from expences where userid='"+ userid + "'";
		ArrayList<ExpencesBean> al = new ArrayList<ExpencesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ExpencesBean eb = new ExpencesBean();
				eb.setExp_id(rs.getInt("exp_id"));
				eb.setExp_ac(rs.getString("exp_ac"));
				eb.setUserid(rs.getInt("userid"));
				eb.setExp_catid(rs.getInt("exp_catid"));
				eb.setAmount(rs.getDouble("amount"));
				eb.setTransaction_date(rs.getString("transaction_date"));
				eb.setPayby(rs.getString("payby"));
				eb.setRemark(rs.getString("remark"));
				
				al.add(eb);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	public ExpencesBean findByExp_Id(int exp_id) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from Expences where exp_id='" + exp_id + "'";
		ExpencesBean eb = new ExpencesBean();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				eb.setExp_id(rs.getInt("exp_id"));
				eb.setExp_ac(rs.getString("exp_ac"));
				eb.setUserid(rs.getInt("userid"));
				eb.setExp_catid(rs.getInt("exp_catid"));
				eb.setAmount(rs.getDouble("amount"));
				eb.setTransaction_date(rs.getString("transaction_date"));
				eb.setPayby(rs.getString("payby"));
				eb.setRemark(rs.getString("remark"));
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return eb;
	}
	
	public String[] ExpenceNameByUserId(int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select exp_catname from Expenses_category where userid='" + userid + "'";
		String optionString = "";
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				optionString += rs.getString("exp_catname");
				optionString += " ";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String strArr[] = optionString.split(" ");
		
		return strArr;
	}
	
	
	public ArrayList<ExpencesBean> findAllByDate(String dateFrom, String dateTo) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from expences where transaction_date>='" + dateFrom + "' && transaction_date<='" + dateTo + "'";
		ArrayList<ExpencesBean> al = new ArrayList<ExpencesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ExpencesBean eb = new ExpencesBean();
				eb.setExp_id(rs.getInt("exp_id"));
				eb.setExp_ac(rs.getString("exp_ac"));
				eb.setUserid(rs.getInt("userid"));
				eb.setExp_catid(rs.getInt("exp_catid"));
				eb.setAmount(rs.getDouble("amount"));
				eb.setTransaction_date(rs.getString("transaction_date"));
				eb.setPayby(rs.getString("payby"));
				eb.setRemark(rs.getString("remark"));
				
				al.add(eb);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
//	public int findIdByICNameICDetails(String inc_catname, String inc_catdetails){
//		Connection connect = ConnectionPool.connectDB();
//		String sql = "select inc_catid from income_category where inc_catname='" + inc_catname + "' and inc_catdetails='" + inc_catdetails + "'";
//		int inc_catid = 0;
//		
//		try {
//			Statement stmt = connect.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				inc_catid = rs.getInt("inc_catid");
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return inc_catid;
//	}
	
	public static void main(String args[]) {
//		ExpencesDAO ed = new ExpencesDAO();
//		ExpencesBean eb = new ExpencesBean();
//		eb.setExp_id(3);
//		eb.setExp_ac("222300021BOI");
//		eb.setUserid(1008);
//		eb.setExp_catid(4);
//		eb.setAmount(5000);
//		eb.setTransaction_date("2000-11-10");
//		eb.setPayby("GooglePay");
//		eb.setRemark("cybrom");
		
//		int r = ed.addExpences(eb);
		
//		int r = ed.updateExpence(eb);
//		if(r > 0) {
//			System.out.println("Expences add succ.");
//		}else {
//			System.out.println("Expences add Failed.");
//		}
//		
//		ArrayList<ExpencesBean> al = ed.findAll();
//		for(ExpencesBean eb : al) {
//			System.out.println(eb);
//		}
		
//		System.out.println(ed.findByExp_Id(3));
		
//		int r = id.deleteIncome(2);
//		if(r>0) {
//			System.out.println("Delete success");
//		}else {
//			System.out.println("Delete failed");
//		}
////		
//		String strArr[] = ed.ExpenceNameByUserId(1008);
//		for(int i = 0; i < strArr.length; i++) {
//			System.out.println(strArr[i]);
//		}
	}
	

}
