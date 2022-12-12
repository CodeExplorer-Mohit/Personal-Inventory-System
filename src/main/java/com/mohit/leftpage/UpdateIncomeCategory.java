package com.mohit.leftpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.bean.Income_CategoryBean;
import com.mohit.dao.Income_CategoryDAO;

/**
 * Servlet implementation class UpdateIncomeCategory
 */
@WebServlet("/UpdateIncomeCategory")
public class UpdateIncomeCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateIncomeCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String incCatName = request.getParameter("incCatName");
		String incCatDetails = request.getParameter("incCatDetails");
		
		int inc_catId = 0;
		try {
			inc_catId = Integer.parseInt(request.getParameter("inc_catId"));
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println("inc_catId: " + inc_catId);
		System.out.println("incCatName: " + incCatName);
		System.out.println("incCatDetails: " + incCatDetails);
		
		Income_CategoryBean icb = new Income_CategoryBean();
		icb.setInc_CatId(inc_catId);
		icb.setInc_CatName(incCatName);
		icb.setInc_CatDetails(incCatDetails);
		
		Income_CategoryDAO icd = new Income_CategoryDAO();
		int r = icd.updateIncCat(icb);
		if(r > 0) {
			/* to the Income Category Page*/
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("IncomeCategory");
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
