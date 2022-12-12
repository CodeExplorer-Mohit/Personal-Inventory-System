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

import com.mohit.dao.ExpencesCategoryDAO;


/**
 * Servlet implementation class UpdateExpencesCategory
 */
@WebServlet("/UpdateExpencesCategory")
public class UpdateExpencesCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExpencesCategory() {
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
		
		int exp_catId = 0;
		try {
			exp_catId = Integer.parseInt(request.getParameter("exp_catId"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		/*from expence_category_update input*/
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println("exp_catId: " + exp_catId);
		System.out.println("expCatName: " + expCatName);
		System.out.println("expCatDetails: " + expCatDetails);
		
		ExpencesCategoryBean ecb = new ExpencesCategoryBean();
		ecb.setExp_catId(exp_catId);
		ecb.setExp_catName(expCatName);
		ecb.setExp_catDetails(expCatDetails);
		
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO();
		int r = ecd.updateExpCat(ecb);
		System.out.println("uec r: " + r);
		if(r > 0) {
			/* to the ExpencesCategory Page*/
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("ExpencesCategory");
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
