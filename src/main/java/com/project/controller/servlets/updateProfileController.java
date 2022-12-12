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
 * Servlet implementation class updateProfileController
 */
@WebServlet("/updateProfileController")
public class updateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfileController() {
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
			userId = Integer.parseInt(request.getParameter("userId"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
				
		System.out.println("userId: " + userId);
			String name = request.getParameter("reguName");
			String userName = request.getParameter("reguUserName");
			String userPass = request.getParameter("reguPassword");
			String userPhon = request.getParameter("reguPhoneNo");
			String userEmai = request.getParameter("reguEmailId");
			String userAdd = request.getParameter("reguAddress");
					
			UsersBean ub = new UsersBean();
			ub.setUserid(userId);
			ub.setName(name);
			ub.setUserName(userName);
			ub.setPassword(userPass);
			ub.setMobile(userPhon);
			ub.setEmail(userEmai);
			ub.setAddress(userAdd);
			
			UsersDAO ud = new UsersDAO();
			int r = ud.updateUser(ub);
			
			if(r > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("id", ub.getUserid());
				out.println("<h1 style='color: green; text-align: center;'>Profile Update Successfully</h1>");
				
				RequestDispatcher rd = request.getRequestDispatcher("UserProfile");
				rd.include(request, response);
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("id", userId);
				
				RequestDispatcher rd = request.getRequestDispatcher("updateProfile");
				rd.forward(request, response);
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
