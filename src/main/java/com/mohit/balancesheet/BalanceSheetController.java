package com.mohit.balancesheet;

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

import com.mohit.bean.ExpencesBean;
import com.mohit.bean.IncomesBean;
import com.mohit.dao.ExpencesDAO;
import com.mohit.dao.IncomesDAO;

/**
 * Servlet implementation class BalanceSheetController
 */
@WebServlet("/BalanceSheetController")
public class BalanceSheetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceSheetController() {
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
			/*id from Balance Sheet */			
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
		out.println("<title>Day Book</title>");
		out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
		
		out.println("<style>");
		out.println(".right-day-book-container{");
		out.println("width: 65vw;");
		out.println("height: 75vh;");
		out.println("float: right;");
		out.println("}");

		out.println(".right-day-book-child{");
		out.println("background-color: #161852;");
		out.println("width: 60vw;");
//		out.println("height: 65vh;");
		out.println("margin: auto;");
		out.println("margin-top: 4vh;");
		out.println("}");


		out.println(".daybook-category-head{");
		out.println("color: white;");
		out.println("font-size: 28px;");
		out.println("font-weight: bold;");
		out.println("padding-top: 20px;");
		out.println("margin-left: 20px;");
		out.println("}");

		out.println(".right-daybook-form-container{");
		out.println("width: 57vw;");
//		out.println("height: 55vh;");
		out.println("margin: auto;");
		out.println("background-color: #070642;");
		out.println("}");

		out.println(".left-daybook-form{");
		out.println("margin-left: 10vw;");
		out.println("padding-top: 5px;");
		out.println("}");
		out.println(".left-daybook-form label{");
		out.println("width: 300px;");
		out.println("height: 20px;");
		out.println("margin-top: 5px;");
		out.println("font-size: 20px;");
		out.println("padding-left: 2px;");
		out.println("font-weight: bold;");
		out.println("color: white;");
		out.println("}");

		out.println(".dbTo{");
		out.println("margin-left: 200px;");
		out.println("}");

		out.println(".left-daybook-form input{");
		out.println("width: 200px;");
		out.println("height: 30px;");
		out.println("margin-top: 5px;");
		out.println("font-size: 22px;");
		out.println("padding-left: 2px;");
		out.println("background-color: white;");
		out.println("}");

		out.println("#dbTo{");
		out.println("margin-left: 10px;");
		out.println("}");

		out.println("#showDB{");
		out.println("margin-left: 10px;");
		out.println("margin-top: 10px;");
		out.println("font-size: 20px;");
		out.println("width: 100px;");
		out.println("height: 35px;");
		out.println("background-color: #484848;");
		out.println("color: white;");
		out.println("font-weight: bold;");
		out.println("}");


		out.println(".left-daybook-table{");
		out.println("margin: auto;");
		out.println("margin-top: 1vh;");
		out.println("width: 45vw;");
		out.println("text-align: left;");
		out.println("}");

		out.println(".left-daybook-table th{"); 
		out.println("color: grey;");
		out.println("background-color: black;");
		out.println("font-size: 21px;");
		out.println("}");

		out.println(".left-daybook-table td{");
		out.println("color: white;");
		out.println("font-size: 18px;");
		out.println("}");
		out.println("</style>");	
		

		
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
				
		out.println("<div class='right-day-book-container'>");	
		out.println("<div class='right-day-book-child'>");
		out.println("<p class='daybook-category-head'>Balance Sheet</p>");
		out.println("<div class='right-daybook-form-container' style='height: 65vh;'>");
		out.println("<form class='left-daybook-form' action='BalanceSheetController'>");
		out.println("<label for='dbDateFrom' class='dbDateFrom'>Date From</label>");
		out.println("<label for='dbTo' class='dbTo'>To</label>");
		out.println("<br>");
		out.println("<input type='text' name='dbDateFrom' id='dDateFrom' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input type='text' name='dbTo' id='dTo' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input id='showDB' type='submit' value='Show'>");
		
		out.println("</form>");
		
		String dateFrom = request.getParameter("bsDateFrom");
		String dateTo = request.getParameter("sbTo"); 
		
		out.println("<table border='1' class='left-daybook-table' style='width: 55vw; line-height:22px;'>");
		out.println("<tr>");
		out.println("<th style='text-align: center; color: white; font-size: 25px;'>Incomes</th>");
		out.println("<th style='text-align: center; color: white; font-size: 25px;'>Expences</th>");
		out.println("</tr>");
		
		out.println("<tr>");
			
		
		out.println("<td>");
		
		out.println("<table border='3' class='left-daybook-table' style='width: 100%; height: 100%; margin: 0; line-height:22px;'>");
		
		out.println("<tr>");
		out.println("<th>incomes</th>");
		out.println("<th>Amount</th>");
		out.println("</tr>");
		
		
		double incomeAmount = 0;
		
		IncomesDAO id = new IncomesDAO();
		ArrayList<IncomesBean> al2 = id.findAllByDate(dateFrom, dateTo);
		for(IncomesBean ib : al2) {
			out.println("<tr>");
			out.println("<td>" + ib.getInc_ac() + "</td>");
			incomeAmount += ib.getAmount();
			out.println("<td>" + ib.getAmount() + "</td>");
			out.println("</tr>");
		}
		
	
					
		
		out.println("<tr>");
		out.println("<th>Total</th>");
		out.println("<th>" + incomeAmount + "</th>");
		out.println("</tr>");
		
		out.println("</td>");
		out.println("</table>");
		
		out.println("</td>");
		
		
		out.println("<td style='height: 0;'>");
		
		out.println("<table border='3' class='left-daybook-table' style='width: 100%; height: 100%; line-height:22px;'>");
		
		out.println("<tr>");
		out.println("<th>expences</th>");
		out.println("<th>Amount</th>");
		out.println("</tr>");
			
		
		double expenceAmount = 0;
		ExpencesDAO ed = new ExpencesDAO();
		ArrayList<ExpencesBean> al1 = ed.findAllByDate(dateFrom, dateTo);
		for(ExpencesBean eb : al1) {
			out.println("<tr>");
			out.println("<td>" + eb.getExp_ac() + "</td>");
			expenceAmount += eb.getAmount();
			out.println("<td>" + eb.getAmount() + "</td>");
			out.println("</tr>");
		}
			
		
		out.println("<tr>");
		out.println("<th>Total</th>");
		out.println("<th>" + expenceAmount + "</th>");
		out.println("</tr>");
		out.println("</table>");
			
		out.println("</td>");
				
		out.println("</tr>");

		double totalAmount = incomeAmount - expenceAmount;
		out.println("<tr>");
		out.println("<th colspan='2' style='text-align: center; color: white; font-size: 25px;'>Gross Amount   :   " + totalAmount + "</th>");
		out.println("</tr>");
						
		out.println("</table>");

		session.setAttribute("id", userId);
		
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
