package com.mohit.expensesCategory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.bean.ExpencesCategoryBean;
import com.mohit.bean.Income_CategoryBean;
import com.mohit.dao.ExpencesCategoryDAO;
import com.mohit.dao.Income_CategoryDAO;

/**
 * Servlet implementation class AddExpencesCategory
 */
@WebServlet("/AddExpencesCategory")
public class AddExpencesCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpencesCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String expCatName = request.getParameter("expCatName");
		String expCatDetails = request.getParameter("expCatDetails");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println("expCatName: " + expCatName);
		System.out.println("expCatDetails: " + expCatDetails);
		System.out.println("userId: " + userId);
		
		ExpencesCategoryBean ecb = new ExpencesCategoryBean();
		ecb.setExp_catName(expCatName);
		ecb.setExp_catDetails(expCatDetails);
		ecb.setUserId(userId);
		
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO();
		ecd.addExpencesCategory(ecb);
		System.out.println(ecb);
		
		int inc_catId = ecd.findIdByECNameECDetails(expCatName, expCatDetails);
		System.out.println("inc_CatId: " + inc_catId);
		
		HttpSession session = request.getSession();
		session.setAttribute("inc_catId", inc_catId);
		session.setAttribute("id", userId);
		RequestDispatcher rd = request.getRequestDispatcher("ExpencesCategory");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
