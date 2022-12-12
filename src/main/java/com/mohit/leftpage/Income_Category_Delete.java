package com.mohit.leftpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mohit.dao.Income_CategoryDAO;

/**
 * Servlet implementation class Income_Category_Delete
 */
@WebServlet("/Income_Category_Delete")
public class Income_Category_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Income_Category_Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
		int inc_catId = 0; 
		
		try {
			/*catId from Income Category table delete Link*/
			inc_catId = Integer.parseInt(request.getParameter("inc_catId"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("catId from Income Category :" + inc_catId);
		
		Income_CategoryDAO icd = new Income_CategoryDAO(); 
		int r = icd.deleteIncCat(inc_catId);
		if(r > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("IncomeCategory");
			rd.forward(request, response);
		}else {
			System.out.println("Income Category cant Delete");
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
