package com.mohit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mohit.bean.UsersBean;
import com.mohit.utility.ConnectionPool;

public class UsersDAO {
	public int addUser(UsersBean ub) {
		Connection connect = ConnectionPool.connectDB();
		int r = 0;
		String sql = "insert into users(username, password, name, address, mobile, email) values('" + ub.getUserName()+ "', '" + ub.getPassword() + "', '" + ub.getName() + "', '" + ub.getAddress() + "', '" + ub.getMobile() + "', '" + ub.getEmail()+ "')";
		
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
	
	
	public int updateUser(UsersBean ub) {
		Connection connect = ConnectionPool.connectDB();
		int r = 0;
		String sql = "update users set username='" + ub.getUserName() + "', password='" + ub.getPassword() + "', name='" + ub.getName() + "', address='" + ub.getAddress() + "', mobile='" + ub.getMobile() + "', email='" + ub.getEmail() + "' where userid='" + ub.getUserid() + "'";
		
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
	
	
	public int deleteUser(int userId) {
		Connection connect = ConnectionPool.connectDB();
		int r = 0;
		String sql= "delete from users where userid='" + userId + "'";
		
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
	
	
	public ArrayList<UsersBean> findAll() {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from users";
		
		ArrayList<UsersBean> al = new ArrayList<UsersBean>();
		
		Statement stmt;
		try {
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				UsersBean ub = new UsersBean();
				ub.setUserid(rs.getInt("userid"));
				ub.setUserName(rs.getString("username"));
				ub.setPassword(rs.getString("password"));
				ub.setName(rs.getString("name"));
				ub.setAddress(rs.getString("address"));
				ub.setMobile(rs.getString("mobile"));
				ub.setEmail(rs.getString("email"));
				
				al.add(ub);
				
				connect.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return al;
	}
	
	
	public UsersBean findByUserId(int userId) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from users where userid='" + userId + "'";
		UsersBean ub = new UsersBean();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ub.setUserid(rs.getInt("userid"));
				ub.setUserName(rs.getString("username"));
				ub.setPassword(rs.getString("password"));
				ub.setName(rs.getString("name"));
				ub.setAddress(rs.getString("address"));
				ub.setMobile(rs.getString("mobile"));
				ub.setEmail(rs.getString("email"));
				
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ub;
	} 
	
	
	public UsersBean findByUserName(String userName) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from users where username='" + userName + "'";
		UsersBean ub = new UsersBean();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ub.setUserid(rs.getInt("userid"));
				ub.setUserName(rs.getString("username"));
				ub.setPassword(rs.getString("password"));
				ub.setName(rs.getString("name"));
				ub.setAddress(rs.getString("address"));
				ub.setMobile(rs.getString("mobile"));
				ub.setEmail(rs.getString("email"));
			
				connect.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ub;
	} 
	
	
	
	public int findIdByUserNamePass(String userName, String userPass) {
		Connection connect = ConnectionPool.connectDB();
		String sql = "select userid from users where username='" + userName + "'and password='" + userPass + "'";
		int id = 0;
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt("userid");
			}
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return id;
	} 
	
	
	
	public boolean checkUserName(String userName){
		Connection connect = ConnectionPool.connectDB();
		String sql = "select * from users where username='" + userName + "'";
		boolean bool = false;
		UsersBean ub = new UsersBean();
		
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ub.setUserid(rs.getInt("userid"));
				ub.setUserName(rs.getString("username"));
				ub.setPassword(rs.getString("password"));
				ub.setName(rs.getString("name"));
				ub.setAddress(rs.getString("address"));
				ub.setMobile(rs.getString("mobile"));
				ub.setEmail(rs.getString("email"));
				
				connect.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			if(!(userName.equals(ub.getUserName()))) {
				bool = true;
			}
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return bool;
	}
	

//	public static void main(String args[]) {
//		UsersDAO ud = new UsersDAO();
//		UsersBean ub = new UsersBean("amitj123tv", "12345", "amit jatav", "bawadiya", "5568542152", "amt@ybl");
//		ud.addUser(ub);
//		UsersBean ub = ud.findByUserName("amitj123tv");
//		System.out.println(ub);
		
//		System.out.println(ud.findIdByUserName("z0m3"));
		
//		System.out.println(ud.findIdByUserNamePass("z0m3", "120120"));
		
//		int r = ud.updateUser(ud.findByUserId(1003));
//		
//		if(r > 0) {
//			System.out.println("update Success.Dao");
//		}else {
//			System.out.println("update Failed.Dao");
//		}
		
//		System.out.println(ud.checkUserName("rht1"));
//	}

}
