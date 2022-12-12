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
 * Servlet implementation class updateProfile
 */
@WebServlet("/updateProfile")
public class updateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfile() {
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
			/*id from UserProfile*/
			/*id from updateProfile*/
			userId = Integer.parseInt(request.getParameter("id"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			HttpSession session = request.getSession();
			userId = (Integer)session.getAttribute("id");
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if(userId == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}else{
			UsersDAO ud = new UsersDAO();
			UsersBean ub = ud.findByUserId(userId);
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='utf-8'>");
			out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			out.println("<title>Home</title>");
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

			out.println("<div class='main-container-left-&-right'>");

			out.println("<div class='left-menu-bar-Master'>");
			out.println("<p class='left-menu-heading-master'>Master</p>");

			out.println(" <div class='left-menu-blackBox'>");
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
			out.println("</div>");

			out.println("<div class='right-update-profile-bar'>");
			out.println("<p>Update User Profile</p>");

			out.println("<form class='right-update-form' action='updateProfileController'>");	
			
			out.println("<input type='text' name='userId' id='uid' value='" + ub.getUserid() + "'>");
			
			out.println("<label for='un' class='userName-update'>Name</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguName' id='un' value='" + ub.getName() + "'>");
			
			out.println("<br>");
			
			out.println("<label for='uun' class='userUName-update'>Username</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguUserName' id='uun' value='" + ub.getUserName() + "'>");
			
			out.println("<br>");
			
			out.println("<label for='up' class='userPass-update'>Password</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguPassword' id='up' value='" + ub.getPassword() + "'>");
			out.println("<br>");
			out.println("<label for='upn' class='userPhon-update'>PhoneNo</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguPhoneNo' id='upn' value='" + ub.getMobile() + "'>");
			out.println("<br>");
			out.println("<label for='uei' class='userEmail-update'>EmailId</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguEmailId' id='uei' value='" + ub.getEmail() + "'>");
			out.println("<br>");
			out.println("<label for='ua' class='userAdd-update'>Address</label>");
			out.println("<span class='update-span-colon'>:</span>");
			out.println("<input type='text' name='reguAddress' id='ua' value='" + ub.getAddress() + "'>");
			out.println("<br>");
			out.println("<input id='input-Update' type='submit' value='Update'>");
			out.println("</form>");

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
