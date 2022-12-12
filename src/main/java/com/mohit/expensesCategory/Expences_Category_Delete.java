package com.mohit.expensesCategory;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.dao.ExpencesCategoryDAO;

/**
 * Servlet implementation class Expences_Category_Delete
 */
@WebServlet("/Expences_Category_Delete")
public class Expences_Category_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Expences_Category_Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
		int exp_catId = 0; 
		
		try {
			/*catId from ExpencesCategory table delete Link*/
			exp_catId = Integer.parseInt(request.getParameter("exp_catId"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		int userId = 0;
		
		try {
			/*userId from ExpencesCategory*/			
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
		
		System.out.println("catId from Expences Category :" + exp_catId);
				
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO(); 
		int r = ecd.deleteExpCat(exp_catId);
		System.out.println("exp_catId: " + exp_catId);
		if(r > 0) {
			/*to ExpencesCategory*/
			HttpSession session = request.getSession();
			System.out.println("id in xpences Category delete: " + userId);
			session.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("ExpencesCategory");
			rd.forward(request, response);
		}else {
			System.out.println("Expences Category cant Delete");
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
