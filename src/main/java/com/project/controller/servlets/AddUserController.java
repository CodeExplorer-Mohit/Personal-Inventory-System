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
 * Servlet implementation class AddUserController
 */
@WebServlet("/AddUserController")
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		PrintWriter out = response.getWriter();
		String uName = request.getParameter("reguName");
		String userName = request.getParameter("reguUserName");
		String userPass = request.getParameter("reguPassword");
		String userConPass = request.getParameter("reguConfPass");
		String userPhon = request.getParameter("reguPhoneNo");
		String userEmai = request.getParameter("reguEmailId");
		String userAdd = request.getParameter("reguAddress");
		
		UsersDAO ud = new UsersDAO();
		boolean condition = ud.checkUserName(userName);
		System.out.println(condition);
		if(condition) {
		
			if(userPass.equals(userConPass)) {
				UsersBean ub = new UsersBean();
				ub.setUserName(userName);
				ub.setPassword(userPass);
				ub.setName(uName);
				ub.setAddress(userAdd);
				ub.setMobile(userPhon);
				ub.setEmail(userEmai);
				
				int r = ud.addUser(ub);
			
				if(r > 0) {
					RequestDispatcher rd = request.getRequestDispatcher("login.html");
					rd.forward(request, response);
					
				}else {
					out.println("<h1 style='color: red; text-align: center;'>password mismatch.</h1>");
					RequestDispatcher rd = request.getRequestDispatcher("registerUser.html");
					rd.include(request, response);
				}
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("registerUser.html");
				rd.forward(request, response);
			}
		
		}else {
			out.println("<h1 style='color: red;'>username is already exist.</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("registerUser.html");
			rd.include(request, response);
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
