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
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("loginuUserName");
		String userPass = request.getParameter("loginuPassword");
		int userId = 0;
		UsersDAO ud = new UsersDAO();
		userId = ud.findIdByUserNamePass(userName, userPass);
		
		if(userId != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			out.println("<h1 style='color: green; text-align: center'>Login Success.</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("homePage");
			rd.forward(request, response);
		}else {
			out.println("<h1 style='color: red; margin-left: 10px;'>Invalid Username and Password</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
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
