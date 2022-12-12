package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.CashBookBean;
import com.mohit.utility.ConnectionPool;

public class CashBookDAO {
	public int addCashBook(CashBookBean cb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into cash_book(account, transaction_date, amount, userid, operation) values('" + cb.getAccount() + "', '" + cb.getTransaction_date() + "', '" + cb.getAmount() + "', '" + cb.getUserid() + "', '" + cb.getOperation() + "')";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	public int updateCashbook(CashBookBean cb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "update cash_book set account='" + cb.getAccount() + "', transaction_date='" + cb.getTransaction_date() + "', amount='" + cb.getAmount() + "', userid='" + cb.getUserid() + "', operation='" + cb.getOperation() + "' where acid='" + cb.getAcid() + "'";
		int r = 0;
		
		try {
			Statement stmt = connect.createStatement();
			r = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return r;
	}
	
	public ArrayList<CashBookBean> findAll() {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from cash_book";
		ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CashBookBean cb = new CashBookBean();
				cb.setAcid(rs.getInt("acid"));
				cb.setAccount(rs.getString("account"));
				cb.setTransaction_date(rs.getString("transaction_date"));
				cb.setAmount(rs.getDouble("amount"));
				cb.setUserid(rs.getInt("userid"));
				cb.setOperation(rs.getString("operation"));
				
				al.add(cb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
	public CashBookBean findCbByCbId(int acid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from cash_book where acid='" + acid + "'";
		CashBookBean cb = new CashBookBean();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				cb.setAcid(rs.getInt("acid"));
				cb.setAccount(rs.getString("account"));
				cb.setTransaction_date(rs.getString("transaction_date"));
				cb.setAmount(rs.getDouble("amount"));
				cb.setUserid(rs.getInt("userid"));
				cb.setOperation(rs.getString("operation"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return cb;
	}
	
	public ArrayList<CashBookBean> findAllByUserID(int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from cash_book where userid='" + userid + "'";
		ArrayList<CashBookBean> al = new ArrayList<CashBookBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CashBookBean cb = new CashBookBean();
				cb.setAcid(rs.getInt("acid"));
				cb.setAccount(rs.getString("account"));
				cb.setTransaction_date(rs.getString("transaction_date"));
				cb.setAmount(rs.getDouble("amount"));
				cb.setUserid(rs.getInt("userid"));
				cb.setOperation(rs.getString("operation"));
				
				al.add(cb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}

	
	public ArrayList<CashBookBean> findCbByFTDate(String dateFrom, String dateTo, int userid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from cash_book where transaction_date > '" + dateFrom + "' && transaction_date < '" + dateTo + "' && userid='" + userid + "'";
		ArrayList<CashBookBean> al = new ArrayList<CashBookBean>(); 
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CashBookBean cb = new CashBookBean();
				cb.setAcid(rs.getInt("acid"));
//				cb.setAccount(rs.getString("account"));
				cb.setTransaction_date(rs.getString("transaction_date"));
				cb.setAmount(rs.getDouble("amount"));
//				cb.setUserid(rs.getInt("userid"));
				cb.setOperation(rs.getString("operation"));
				al.add(cb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
	public static void main(String args[]) {
		CashBookDAO cd = new CashBookDAO();
//		CashBookBean cb = new CashBookBean();
//		cb.setAcid(4);
//		cb.setAccount("cenraBank1510002");
//		cb.setTransaction_date("2010-01-21");
//		cb.setAmount(1200);
//		cb.setUserid(1007);
//		cb.setOperation("pata nahin");
//		
//		int r = cd.updateCashbook(cb);
//		
//		if(r > 0) {
//			System.out.println("cashbook update");
//		}else {
//			System.out.println("cashbook update Failed.");
//		}
		
		ArrayList<CashBookBean> al = cd.findCbByFTDate("2016-12-05", "2023-12-10", 1019);
		for(CashBookBean cb : al) {
			System.out.println(cb);
		}
		
//		System.out.println(cd.findCbByCbId(4));
		
	}
	
	
}
