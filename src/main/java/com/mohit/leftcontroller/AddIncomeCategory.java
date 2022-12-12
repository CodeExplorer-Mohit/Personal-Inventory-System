package com.mohit.leftcontroller;

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
 * Servlet implementation class AddIncomeCategory
 */
@WebServlet("/AddIncomeCategory")
public class AddIncomeCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddIncomeCategory() {
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
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println("incCatName: " + incCatName);
		System.out.println("incCatDetails: " + incCatDetails);
		System.out.println("userId: " + userId);
		
		Income_CategoryBean icb = new Income_CategoryBean();
		icb.setInc_CatName(incCatName);
		icb.setInc_CatDetails(incCatDetails);
		icb.setUserid(userId);
		
		Income_CategoryDAO icd = new Income_CategoryDAO();
		icd.addIncomeCategory(icb);
		System.out.println(icb);
		
		int inc_catId = icd.findIdByICNameICDetails(incCatName, incCatDetails);
		System.out.println("inc_CatId: " + inc_catId);
		
		HttpSession session = request.getSession();
		session.setAttribute("inc_catId", inc_catId);
		session.setAttribute("id", userId);
		RequestDispatcher rd = request.getRequestDispatcher("IncomeCategory");
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
