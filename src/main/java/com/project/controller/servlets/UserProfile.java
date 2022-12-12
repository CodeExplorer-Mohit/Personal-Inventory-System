package com.project.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.bean.UsersBean;
import com.mohit.dao.UsersDAO;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int userId = 0;
		
		try {
			/*id from homePage*/
			/*id from updateProfile*/
			userId = Integer.parseInt(request.getParameter("id"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			/*id from updateProfileController*/
			HttpSession session = request.getSession();
			userId = (Integer)session.getAttribute("id");
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		if(userId == 0) {
			out.println("<h1 class='hideMeAfter5Seconds' style='color: red; text-align: center'>Please login first.</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
			
		}else {
			UsersDAO ud = new UsersDAO();
			UsersBean ub = ud.findByUserId(userId);
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='utf-8'>");
			out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.println("<title>Profile</title>");
			out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div id='container'>");
			out.println("<div class='head'>");
			out.println("<div class='logo'>");
			out.println("<img src='images/pis-logo.png'>");
			out.println("</div>");
			out.println("<div class='menu'>");
			out.println("<ul class='menu-ul'>");	
			out.println("<li class='menu-ul-li'><a href='homePage?id=" + userId + "'>Home</a></li>");
			out.println("<li class='menu-ul-li'><a href='UserProfile?id=" + userId + "'>Profile</a></li>");
			out.println("<li class='menu-ul-li'><a href='updateProfile?id=" + userId + "'>Update Profile</a></li>");
			out.println("<li class='menu-ul-li'><a href='logoutController?id=" + userId + "'>Logout</a></li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class='left-menu-bar-Master'>");
			out.println("<p class='left-menu-heading-master'>Master</p>");
			out.println("<div class='left-menu-blackBox'>");
			out.println("<ul class='left-menu-ul'>");		
			out.println("<li class='left-menu-ul-li'><a href='ExpencesCategory?id=" + userId + "'>Expenses Category</a></li>");
			out.println("<li class='left-menu-ul-li'><a href='IncomeCategory?id=" + userId + "'>Income Category</a></li>");
			out.println("<li class='left-menu-ul-li'><a href='ExpencesForm?id=" + userId + "'>Expenses</a></li>");
			out.println("<li class='left-menu-ul-li'><a href='IncomeForm?id=" + userId + "'>Income</a></li>");
			out.println("<li class='left-menu-ul-li'><a href='CashBook?id=" + userId + "'>Cash Book</a></li>");	
			out.println("<li class='left-menu-ul-li'><a href='BankBook?id=" + userId + "'>Bank Book</a></li>");	
			out.println("<li class='left-menu-ul-li'><a href='DayBook?id=" + userId + "'>Day Book</a></li>");	
			out.println("<li class='left-menu-ul-li'><a href='BalanceSheet?id=" + userId + "'>Balance Sheet</a></li>");	
			out.println("</ul>");
			out.println("</div>");
			
			out.println("<div class='perent-right-profile-container' style='width=: 1060px;'>");		
			out.println("<div class='perent-right-child' style='margin-top: -52vh; margin-left: 75vw'>");
			out.println("<p class='perent-right-child-p'>User Profile</p>");
			
			out.println("<table class='perent-right-child-table' border='0'>");
			out.println("<tr>");
			out.println("<td><p class='tbl-tr-td'>Name</p></td>");
			out.println("<td class='tbl-tr-colon'>:</td>");
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getName() + "</p></td>");	
			out.println("</tr>");	
			out.println("<tr>");	
			out.println("<td><p class='tbl-tr-td'>Username</p></td>");	
			out.println("<td class='tbl-tr-colon'>:</td>");			
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getUserName() + "</p></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><p class='tbl-tr-td'>Password</p></td>");
			out.println("<td class='tbl-tr-colon'>:</td>");
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getPassword() + "</p></td>");
			out.println("</tr>");
								
			out.println("<tr>");
			out.println("<td><p class='tbl-tr-td'>PhoneNo</p></td>");
			out.println("<td class='tbl-tr-colon'>:</td>");
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getMobile() + "</p></td>");
			out.println("</tr>");
								
			out.println("<tr>");
			out.println("<td><p class='tbl-tr-td'>EmailId</p></td>");
			out.println("<td class='tbl-tr-colon'>:</td>");
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getEmail() + "</p></td>");
			out.println("</tr>");
								
			out.println(" <tr>");
			out.println("<td><p class='tbl-tr-td'>Adress</p></td>");
			out.println("<td class='tbl-tr-colon'>:</td>");
			out.println("<td><p class='tbl-tr-td-value'>" + ub.getAddress() + "</p></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
