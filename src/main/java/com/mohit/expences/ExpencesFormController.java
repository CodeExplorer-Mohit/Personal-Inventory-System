package com.mohit.expences;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohit.bean.BankBookBean;
import com.mohit.bean.CashBookBean;
import com.mohit.bean.ExpencesBean;
import com.mohit.dao.BankBookDAO;
import com.mohit.dao.CashBookDAO;
import com.mohit.dao.ExpencesCategoryDAO;
import com.mohit.dao.ExpencesDAO;

/**
 * Servlet implementation class ExpencesFormController
 */
@WebServlet("/ExpencesFormController")
public class ExpencesFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpencesFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("this is Expences form controller");
		
		int userId = Integer.parseInt(request.getParameter("expuserId"));
		String category = request.getParameter("expCategory");
		double amount = Double.parseDouble(request.getParameter("expAmount"));
		String payBy = request.getParameter("expPayBy");
		String date = request.getParameter("expDate");
		String remark = request.getParameter("expRemark");
		
		if(payBy.equals("cash")) {
			CashBookBean cb = new CashBookBean();
			cb.setTransaction_date(date);
			cb.setAmount(amount);
			cb.setUserid(userId);
			cb.setOperation("Pay");
			
			CashBookDAO cd = new CashBookDAO();
			cd.addCashBook(cb);
		}
		
		if(payBy.equals("check")) {
			BankBookBean bb = new BankBookBean();
			bb.setTransaction_date(date);
			bb.setAmount(amount);
			bb.setUserid(userId);
			bb.setOperation("Pay");
			
			BankBookDAO bd = new BankBookDAO();
			bd.addBankBook(bb);
		}
		
		/*find exp_catId using category Name*/
		ExpencesCategoryDAO ecd = new ExpencesCategoryDAO();  
		int exp_catId = ecd.findIdByECName(category);
		ExpencesBean eb = new ExpencesBean();
		eb.setUserid(userId);
		eb.setExp_ac(category);
		eb.setExp_catid(exp_catId);
		eb.setAmount(amount);
		eb.setTransaction_date(date);
		eb.setPayby(payBy);
		eb.setRemark(remark);	
		ExpencesDAO id = new ExpencesDAO(); 
		int r = id.addExpences(eb);
		
		if(r > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			session.setAttribute("r", r);
			out.println("<h1 style='color: green;'>Income Added Successfully.</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("ExpencesForm");
			rd.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("ExpencesForm");
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
