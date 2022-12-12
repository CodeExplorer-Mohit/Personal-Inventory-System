package com.mohit.cashbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.bean.CashBookBean;
import com.mohit.dao.CashBookDAO;

/**
 * Servlet implementation class CashBook
 */
@WebServlet("/CashBook")
public class CashBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CashBook() {
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
			userId = Integer.parseInt(request.getParameter("id"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		try {
			/*id from CashBook Controller*/			
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
			
		session.setAttribute("id", userId);
			
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("<title>Cash Book</title>");
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
		out.println("</div>");
				
		out.println("<div class='right-cash-book-container'>");	
		out.println("<div class='right-cash-book-child'>");
		out.println("<p class='cashbook-category-head'>CashBook</p>");
		out.println("<div class='right-cashbook-form-container'>");
		out.println("<form class='left-cashbook-form' action='CashBookController'>");
		out.println("<label for='cbDateFrom' class='cbDateFrom'>Date From</label>");
		out.println("<label for='cbTo' class='cbTo'>To</label>");
		out.println("<br>");
		out.println("<input type='text' name='cbDateFrom' id='cbDateFrom' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input type='text' name='cbTo' id='cbTo' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input id='showCB' type='submit' value='Show'>");
		out.println("</form>");
		out.println("<table border='0' class='left-cashbook-table'>");
		out.println("<tr>");
		out.println("<th>S.No</th>");
		out.println("<th>Date</th>");
		out.println("<th>Amount</th>");
		out.println("<th>Pay/Receive</th>");
		out.println("</tr>");
		
//		String dateFrom = "";
//		String dateTo = "";
//		try {
//			/*dateFrom from CashBook Controller*/
//			/*dateTo from CashBook Controller*/
//			dateFrom = (String)session.getAttribute("dateFrom");
//			dateTo = (String)session.getAttribute("dateTo");
//		}catch (NullPointerException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		int sn = 1;
//		CashBookDAO cd = new CashBookDAO();
//		ArrayList<CashBookBean> al = cd.findCbByFTDate(dateFrom, dateTo);
//		for(CashBookBean cb : al) {
//		System.out.println(cb);
//		System.out.println(cb.getTransaction_date());
//		System.out.println(cb.getAmount());
//		System.out.println(cb.getOperation());
//		out.println("<tr>");
//		out.println("<td>" + sn + "</td>");
//		out.println("<td>" + cb.getTransaction_date() + "</td>");
//		out.println("<td>" + cb.getAmount() + "</td>");
//		out.println("<td>" + cb.getOperation() + "</td>");
//		out.println("</tr>");
//		sn++;
//		}
//		
		double totalAmount = 0.00;
		int sn = 1;
		CashBookDAO cd = new CashBookDAO();
		ArrayList<CashBookBean> al = cd.findAllByUserID(userId);
		for(CashBookBean cb : al) {
		out.println("<tr>");
		out.println("<td>" + sn + "</td>");
		out.println("<td>" + cb.getTransaction_date() + "</td>");
		out.println("<td>" + cb.getAmount() + "</td>");
		out.println("<td>" + cb.getOperation() + "</td>");
		out.println("</tr>");
		totalAmount += cb.getAmount();
		sn++;
		}
		
		out.println("<tr>");
		out.println("<th colspan='2'>closing Balance</th>");				
		out.println("<th>" + totalAmount + "</th>");
		out.println("<th></th>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
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
