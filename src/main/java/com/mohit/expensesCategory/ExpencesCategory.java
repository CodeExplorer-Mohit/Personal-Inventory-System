package com.mohit.expensesCategory;

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

import com.mohit.bean.ExpencesCategoryBean;
import com.mohit.dao.ExpencesCategoryDAO;

/**
 * Servlet implementation class ExpencesCategory
 */
@WebServlet("/ExpencesCategory")
public class ExpencesCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpencesCategory() {
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
			/*userId from UpdateIncomeCategory*/
			/*userId from AddExpencesController*/
			/*userId from Expences_Category_deltee*/
			
			HttpSession session = request.getSession();
			userId = (Integer)session.getAttribute("id");
			System.out.println("id dfrom Expences Category delete: " + userId);
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}catch (ClassCastException e) {
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
				
			out.println("<div class='right-Expences-category-container'>");
			out.println("<div class='right-Expences-category-child'>");
			out.println("<div class='right-Expences-category-form-container' style='width: 60vw; height: 30vh; background-color: none;'>");
			out.println("<p class='Expences-category-head'>Expences Category</p>");
					
			out.println("<form action='AddExpencesCategory' class='right-Expences-form' >");
			out.println("<label for='incCN' class='expCatNme'>Category Name:</label>");
			out.println("<input type='text' name='expCatName' id='incCN' placeholder='enter category name' required='required'>");
			out.println("<br>");
			out.println("<label for='incCD' class='expCatDetails'>Category Details:</label>");
			out.println("<input type='text' name='expCatDetails' id='incCD' placeholder='enter category details' required='required'>");
			out.println("<br>");
			out.println("<input type='text' name='userId' id='expCN' value='" + userId + "' style='display: none;'>");
			out.println("<input id='AddExpences' type='submit' value='Add'>");
			out.println("</form>");

			out.println("</div>");

			out.println("<hr size='10px' color='grey'>");

			out.println("<div class='right-Expences-category-infotable'>");
			out.println("<table border='0' class='right-Expences-category-table'>");
			out.println("<tr>");
			out.println("<th class='Expences-th'>Category Name</th>");
			out.println("<th class='Expences-th'>Category Details</th>");
			out.println("<th class='Expences-th'>Edit</th>");
			out.println("<th class='Expences-th'>Delete</th>");
			out.println("</tr>");
		
		HttpSession session = request.getSession();
		/*userId to Expences_Category_update*/	
		session.setAttribute("id", userId);
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO();
		ArrayList<ExpencesCategoryBean> al = ecd.findAll(userId);
		for(ExpencesCategoryBean ecb: al) {
			response.setContentType("text/html");
			out.println("<tr>");
			out.println("<td >" + ecb.getExp_catName() + "</td>");
			out.println("<td>" + ecb.getExp_catDetails() + "</td>");
			out.println("<td><a href='Expences_Category_update?exp_catId=" + ecb.getExp_catId() + "'>Edit</a></td>");
			out.println("<td><a href='Expences_Category_Delete?exp_catId=" + ecb.getExp_catId() + "'>Delete</a></td>");
			out.println("</tr>");
		}				
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
