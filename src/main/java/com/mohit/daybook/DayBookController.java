package com.mohit.daybook;

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
 * Servlet implementation class DayBookController
 */
@WebServlet("/DayBookController")
public class DayBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DayBookController() {
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
			/*id from DayBook */			
			userId = (Integer)session.getAttribute("id");
			session.setAttribute("id", userId);
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
		out.println("<li class='left-menu-ul-li'><a href=''>Balance Sheet</a></li>");
		out.println("</ul>");
		out.println("</div>");
		out.println("</div>");
				
		out.println("<div class='right-day-book-container' style='width: 65vw; height: 75vh; float: right;'>");	
		out.println("<div class='right-day-book-child' style='background-color: #161852; width: 60vw; height: 65vh; margin: auto; margin-top: 4v;'>");
		out.println("<p class='daybook-category-head' style='color: white; font-size: 28px; font-weight: bold; padding-top: 20px; margin-left: 20px;'>Day Book</p>");
		out.println("<div class='right-daybook-form-container' style='height: 65vh;width: 57vw; height: 55vh; margin: auto; background-color: #070642;'>");
		out.println("<form class='left-daybook-form' action='DayBookController' style='margin-left: 10vw; padding-top: 5px;'>");
		out.println("<label for='dbDateFrom' class='dbDateFrom'>Date From</label>");
		out.println("<label for='dbTo' class='dbTo'>To</label>");
		out.println("<br>");
		out.println("<input type='text' name='dDateFrom' id='dDateFrom' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input type='text' name='dbTo' id='dTo' placeholder='yyyy-mm-dd' required='required'>");
		out.println("<input id='showDB' type='submit' value='Show'>");
		out.println("</form>");
		out.println("<table border='0' class='left-daybook-table' style='width: 55vw; line-height:22px;'>");
		out.println("<tr>");
		out.println("<th>S.No</th>");
		out.println("<th>Account Name</th>");
		out.println("<th>Date</th>");
		out.println("<th>Amount</th>");
		out.println("<th>Pay/Receive</th>");
		out.println("<th>Remark</th>");
		out.println("</tr>");

		
		out.println("<tr><td height='15px'><p class='InExPeragraph'>Expences</td></tr></p>");
		
		String dateFrom = request.getParameter("dbDateFrom");
		String dateTo = request.getParameter("dbTo");
		
		int sn1 = 1;
		ExpencesDAO ed = new ExpencesDAO();
		ArrayList<ExpencesBean> al1 = ed.findAllByDate(dateFrom, dateTo);
		for(ExpencesBean eb : al1) {
			out.println("<td>" + sn1 + "</td>");
			out.println("<td>" + eb.getExp_ac() + "</td>");
			out.println("<td>" + eb.getTransaction_date() + "</td>");
			out.println("<td>" + eb.getAmount() + "</td>");
			out.println("<td>" + eb.getPayby() + "</td>");
			out.println("<td>" + eb.getRemark() + "</td>");
			out.println("</tr>");
			sn1++;
		}
		
		
		out.println("<tr><td height='15px'><p class='InExPeragraph'>Incomes</td></tr></p>");
		
		
		int sn2 = 1;
		IncomesDAO id = new IncomesDAO();
		ArrayList<IncomesBean> al2 = id.findAllByDate(dateFrom, dateTo);
		for(IncomesBean eb : al2) {
			out.println("<td>" + sn2 + "</td>");
			out.println("<td>" + eb.getInc_ac() + "</td>");
			out.println("<td>" + eb.getTransaction_date() + "</td>");
			out.println("<td>" + eb.getAmount() + "</td>");
			out.println("<td>" + eb.getReceiveby() + "</td>");
			out.println("<td>" + eb.getRemark() + "</td>");
			out.println("</tr>");
			sn2++;
		}
		
		
					
		
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
//		double totalAmount = 0.00;
//		int sn = 1;
//		CashBookDAO cd = new CashBookDAO();
//		ArrayList<CashBookBean> al = cd.findAll();
//		for(CashBookBean cb : al) {
//		out.println("<tr>");
//		out.println("<td>" + sn + "</td>");
//		out.println("<td>" + cb.getTransaction_date() + "</td>");
//		out.println("<td>" + cb.getAmount() + "</td>");
//		out.println("<td>" + cb.getOperation() + "</td>");
//		out.println("</tr>");
//		totalAmount += cb.get	Amount();
//		sn++;
//		}
		
//		out.println("<tr>");
//		out.println("<th colspan='2'>closing Balance</th>");				
//		out.println("<th>" + totalAmount + "</th>");
//		out.println("<th></th>");
//		out.println("</tr>");
		
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
