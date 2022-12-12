package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mohit.bean.IncomesBean;
import com.mohit.utility.ConnectionPool;

public class IncomesDAO {
	public int addIncome(IncomesBean ib) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into incomes(inc_ac, userid, inc_catid, amount, transaction_date, receiveby, remark) values('" + ib.getInc_ac() + "', '" + ib.getUserid() + "', '" + ib.getInc_catid() + "', '" + ib.getAmount() + "', '" + ib.getTransaction_date() + "', '" + ib.getReceiveby() + "', '" + ib.getRemark() + "')";
		String sql1 = "insert into cash_book(transactiion_date, amount, userid, operation) values('" + ib.getTransaction_date() + "', '" + ib.getAmount() + "', '" + ib.getUserid() + "', 'Recieve')";
		int r = 0;
		int r1 = 0;
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
			r1 = stmt.executeUpdate(sql1);
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		System.	out.println("r1:" + r1);
		
		return r;
	}
	
	
	public int updateIncome(IncomesBean ib) {
		Connection connect = ConnectionPool.connectDB(); 
		String sql = "update incomes set inc_ac='" + ib.getInc_ac() + "', amount='" + ib.getAmount() + "', transaction_date='" + ib.getTransaction_date() + "', receiveby='" + ib.getReceiveby() + "', remark='" + ib.getRemark() + "' where inc_id='" + ib.getInc_id() + "'";
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
	
	public int deleteIncome(int inc_id) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "delete from incomes where inc_id='" + inc_id + "'";
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
	
	
	public ArrayList<IncomesBean> findAll() {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from incomes";
		ArrayList<IncomesBean> al = new ArrayList<IncomesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				IncomesBean ib = new IncomesBean();
				ib.setInc_id(rs.getInt("inc_id"));
				ib.setInc_ac(rs.getString("inc_ac"));
				ib.setUserid(rs.getInt("userid"));
				ib.setInc_catid(rs.getInt("inc_catid"));
				ib.setAmount(rs.getDouble("amount"));
				ib.setTransaction_date(rs.getString("transaction_date"));
				ib.setReceiveby(rs.getString("receiveby"));
				ib.setRemark(rs.getString("remark"));
				
				al.add(ib);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	
	public ArrayList<IncomesBean> findAllByUi(int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from incomes where userid='" + userid + "'";
		ArrayList<IncomesBean> al = new ArrayList<IncomesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				IncomesBean ib = new IncomesBean();
				ib.setInc_id(rs.getInt("inc_id"));
				ib.setInc_ac(rs.getString("inc_ac"));
				ib.setUserid(rs.getInt("userid"));
				ib.setInc_catid(rs.getInt("inc_catid"));
				ib.setAmount(rs.getDouble("amount"));
				ib.setTransaction_date(rs.getString("transaction_date"));
				ib.setReceiveby(rs.getString("receiveby"));
				ib.setRemark(rs.getString("remark"));
				
				al.add(ib);
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return al;
	}
	
	
	
	public IncomesBean findByInc_Id(int inc_id) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from incomes where inc_id='" + inc_id + "'";
		IncomesBean ib = new IncomesBean();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ib.setInc_id(rs.getInt("inc_id"));
				ib.setInc_ac(rs.getString("inc_ac"));
				ib.setUserid(rs.getInt("userid"));
				ib.setInc_catid(rs.getInt("inc_catid"));
				ib.setAmount(rs.getDouble("amount"));
				ib.setTransaction_date(rs.getString("transaction_date"));
				ib.setReceiveby(rs.getString("receiveby"));
				ib.setRemark(rs.getString("remark"));
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		return ib;
	}
	
	public String[] incomeNameByUserId(int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select inc_catname from income_category where userid='" + userid + "'";
		String optionString = "";
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				optionString += rs.getString("inc_catname");
				optionString += " ";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String strArr[] = optionString.split(" ");
		
		return strArr;
	}
	
	public ArrayList<IncomesBean> findAllByDate(String dateFrom, String dateTo) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from incomes where transaction_date>='" + dateFrom + "' && transaction_date<='" + dateTo + "'";
		ArrayList<IncomesBean> al = new ArrayList<IncomesBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				IncomesBean ib = new IncomesBean();
				ib.setInc_id(rs.getInt("inc_id"));
				ib.setInc_ac(rs.getString("inc_ac"));
				ib.setUserid(rs.getInt("userid"));
				ib.setInc_catid(rs.getInt("inc_catid"));
				ib.setAmount(rs.getDouble("amount"));
				ib.setTransaction_date(rs.getString("transaction_date"));
				ib.setReceiveby(rs.getString("receiveby"));
				ib.setRemark(rs.getString("remark"));
				
				al.add(ib);
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
	
	
	public static void main(String arga[]) {
//		IncomesDAO id = new IncomesDAO();
//		IncomesBean ib = new IncomesBean();
//		ib.setInc_id(4);
//		ib.setInc_ac("222300021BOI");
//		ib.setUserid(1005);
//		ib.setInc_catid(6);
//		ib.setAmount(35000);
//		ib.setTransaction_date("2000-11-10");
//		ib.setReceiveby("GooglePay");
//		ib.setRemark("DJ site");
		
//		int r = id.updateIncome(ib);
//		if(r > 0) {
//			System.out.println("Income update.");
//		}else {
//			System.out.println("Income update Failed.");
//		}
//		
//		ArrayList<IncomesBean> al = id.findAll();
//		for(IncomesBean ib : al) {
//			System.out.println(ib);
//		}
		
//		System.out.println(id.findByInc_Id(4));
		
//		int r = id.deleteIncome(2);
//		if(r>0) {
//			System.out.println("Delete success");
//		}else {
//			System.out.println("Delete failed");
//		}
//		
//		String strArr[] = id.incomeNameByUserId(1004);
//		for(int i = 0; i < strArr.length; i++) {
//			System.out.println(strArr[i]);
//		}
		
	}

}
