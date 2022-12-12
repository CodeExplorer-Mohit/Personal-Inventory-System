package com.mohit.expences;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.dao.ExpencesDAO;

/**
 * Servlet implementation class ExpencesForm
 */
@WebServlet("/ExpencesForm")
public class ExpencesForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpencesForm() {
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
		
		try {
			/*id from ExpencesForm Controller*/			
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
//
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
					
			out.println("<div class='right-Expences-container'>");
			out.println("<div class='right-Expences-child'>");
			out.println("<span class='Expences-head'>Expences</span>");
			
			int r = 11;
			try {
				/* value of R Form ExpencesForm Controller*/			
				HttpSession session = request.getSession();
				r = (Integer)session.getAttribute("r");
			}catch (NullPointerException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			String sucMessage = "none";
			String canMessage = "none";
			if(r > 0 && r != 11) {
				sucMessage = "";
				canMessage = "none";
			}else if(r == 11 ){
				sucMessage = "none";
				canMessage = "none";
			}else{
				sucMessage = "none";
				canMessage = "";
			}
			
			out.println("<span class='.Expences-head-failed' style='display:" + canMessage + "; color: red; font-size: 25px; padding-left:185px; font-weight: bold;'>Expence Added Failed.</span>");
			out.println("<span class='.income-head-success' style='display:" + sucMessage + "; color: green; font-size: 25px; padding-left:185px; font-weight: bold;'>Expence Added Success.</span>");
			HttpSession session = request.getSession();
			session.setAttribute("r", 11);
			
			out.println("<div class='right-Expences-form-container'>");
			
			out.println("<form class='right-Expences-form' action='ExpencesFormController'>");
			out.println("<label for='expCN' class='Expence'>Expence:</label>");
			out.println("<input type='text' name='Expence' id='expCN' placeholder='bill/ticket/stationary' required='required'>");
			out.println("<br>");
			out.println("<label for='expCD' class='expCategory'>Category:</label>");
			out.println("<select name='expCategory' id='ExpCD' style='width: 200px;'>");
			
			ExpencesDAO ed = new ExpencesDAO();
			String strArr[] = ed.ExpenceNameByUserId(userId);
			for(int i = 0; i < strArr.length; i++) {
				String optionString = strArr[i];
				
				out.println("<option>" + optionString + "</option>");
			}
			response.setContentType("text/html");
			out.println("</select>");
			out.println("<br>");
			
			response.setContentType("text/html");
			out.println("<input type='text' name='expuserId' id='expU' value='" + userId + "' style='display: none;'>");
			
			out.println("<label for='expA' class='expAmount'>Amount:</label>");
			out.println("<input type='text' name='expAmount' id='expA' placeholder='enter amount' required='required'>");
			out.println("<br>");
			out.println("<label for='expPB' class='PayBy'>PayBy:</label>");
			out.println("<select name='expPayBy' id='expPB' style='width: 200px; margin-left: 58px;'>");
			out.println("<option>cash</option>");
			out.println("<option>check</option>");
			out.println("</select>");
			out.println("<br>");
			out.println("<label for='expD' class='expDate'>Date:</label>");
			out.println("<input type='text' name='expDate' id='expD' placeholder='yyyy-mm-dd' required='required'>");
			out.println("<br>");
			out.println("<label for='expR' class='expRemark'>Remark:</label>");
			out.println("<input type='text' name='expRemark' id='expR' required='required'>");
			out.println("<br>");
			out.println("<input id='expAddExpences' type='submit' value='Add Expence'>");
			out.println("</form>");
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
