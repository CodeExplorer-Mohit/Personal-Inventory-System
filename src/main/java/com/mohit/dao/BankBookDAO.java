package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.BankBookBean;
import com.mohit.bean.CashBookBean;
import com.mohit.utility.ConnectionPool;

public class BankBookDAO {
	public int addBankBook(BankBookBean cb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "insert into bank_book(account, transaction_date, amount, userid, operation) values('" + cb.getAccount() + "', '" + cb.getTransaction_date() + "', '" + cb.getAmount() + "', '" + cb.getUserid() + "', '" + cb.getOperation() + "')";
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
	
	public int updateBankbook(BankBookBean cb) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "update bank_book set account='" + cb.getAccount() + "', transaction_date='" + cb.getTransaction_date() + "', amount='" + cb.getAmount() + "', userid='" + cb.getUserid() + "', operation='" + cb.getOperation() + "' where acid='" + cb.getAcid() + "'";
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
	
	public ArrayList<BankBookBean> findAll() {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from bank_book";
		ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BankBookBean bb = new BankBookBean();
				bb.setAcid(rs.getInt("acid"));
				bb.setAccount(rs.getString("account"));
				bb.setTransaction_date(rs.getString("transaction_date"));
				bb.setAmount(rs.getDouble("amount"));
				bb.setUserid(rs.getInt("userid"));
				bb.setOperation(rs.getString("operation"));
				
				al.add(bb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
	public ArrayList<BankBookBean> findAllByUi(int userid){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from bank_book where userid='" + userid + "'";
		ArrayList<BankBookBean> al = new ArrayList<BankBookBean>();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BankBookBean bb = new BankBookBean();
				bb.setAcid(rs.getInt("acid"));
				bb.setAccount(rs.getString("account"));
				bb.setTransaction_date(rs.getString("transaction_date"));
				bb.setAmount(rs.getDouble("amount"));
				bb.setUserid(rs.getInt("userid"));
				bb.setOperation(rs.getString("operation"));
				
				al.add(bb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
	public BankBookBean findCbByCbId(int acid) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from bank_book where acid='" + acid + "'";
		BankBookBean bb = new BankBookBean();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bb.setAcid(rs.getInt("acid"));
				bb.setAccount(rs.getString("account"));
				bb.setTransaction_date(rs.getString("transaction_date"));
				bb.setAmount(rs.getDouble("amount"));
				bb.setUserid(rs.getInt("userid"));
				bb.setOperation(rs.getString("operation"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return bb;
	}
	
	public ArrayList<BankBookBean> findBbByFTDate(String dateFrom, String dateTo) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from bank_book where transaction_date > '" + dateFrom + "' && transaction_date < '" + dateTo + "'";
		ArrayList<BankBookBean> al = new ArrayList<BankBookBean>(); 
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BankBookBean bb = new BankBookBean();
				bb.setAcid(rs.getInt("acid"));
//				cb.setAccount(rs.getString("account"));
				bb.setTransaction_date(rs.getString("transaction_date"));
				bb.setAmount(rs.getDouble("amount"));
//				cb.setUserid(rs.getInt("userid"));
				bb.setOperation(rs.getString("operation"));
				al.add(bb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
//	public static void main(String args[]) {
//		BankBookDAO bd = new BankBookDAO();
//		BankBookBean bb = new BankBookBean();
//		bb.setAcid(4);
//		bb.setAccount("cenraBank1510002");
//		bb.setTransaction_date("2010-10-11");
//		bb.setAmount(1000.0);
//		bb.setUserid(1004);
//		bb.setOperation("Pay");
//		
//		System.out.println(bd.findCbByCbId(4));
	
//		int r = bd.addBankBook(bb);
		
//		int r = bd.updateBankbook(bb);
//		
//		if(r > 0) {
//			System.out.println("bankbook update");
//		}else {
//			System.out.println("bankbook update Failed.");
//		}
		
//		ArrayList<BankBookBean> al = bd.findAll();
//		for(BankBookBean bb : al) {
//			System.out.println(bb);
//		}
		
		
//	}
	
}
