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
 * Servlet implementation class homePage
 */
@WebServlet("/homePage")
public class homePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Http Servlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int userId = 0;
		
		try {
			/*id from loginController*/			
			HttpSession session = request.getSession();
			userId = (Integer)session.getAttribute("id");
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			/*id from useerProfile*/
			/*id from updateProfile*/
			userId = Integer.parseInt(request.getParameter("id"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		
		
		String linkRU = "";
		String linkRU_name = "";
		
		String linkLL = "";
		String linkLL_name = "";

		if(userId == 0) {
			/*==(register.html , Register User)==*/
			linkRU = "registerUser.html";
			linkRU_name = "Register User";
			
			/*==(login.html , Login)==*/
			linkLL = "login.html";
			linkLL_name = "Login";
		}else {
			/*==(updateProfile , Update Profile)== */
			linkRU = "updateProfile?id=" + userId;
			linkRU_name = "Update Profile";
			
			/*==(logoutController , Logout)== */
			linkLL = "logoutController?id= + userId";
			linkLL_name = "Logout";
		}
		
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
		
		out.println("<li class='menu-ul-li'><a href='homePage'>Home</a></li>");
		out.println("<li class='menu-ul-li'><a href='UserProfile?id=" + userId + "'>Profile</a></li>");									
		out.println("<li class='menu-ul-li'><a href='" + linkRU + "'>" + linkRU_name + "</a></li>");								
		out.println("<li class='menu-ul-li'><a href='" + linkLL + "'>" + linkLL_name + "</a></li>");
		
		out.println("</ul>");
		out.println("</div>");
		out.println("</div>");

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
				
		out.println("<div class='right-img-home'>");
		out.println("<img src='images/home-img.png'>");
		out.println("</div>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
