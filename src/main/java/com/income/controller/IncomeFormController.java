package com.income.controller;

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
import com.mohit.bean.IncomesBean;
import com.mohit.dao.BankBookDAO;
import com.mohit.dao.CashBookDAO;
import com.mohit.dao.Income_CategoryDAO;
import com.mohit.dao.IncomesDAO;

/**
 * Servlet implementation class IncomeFormController
 */
@WebServlet("/IncomeFormController")
public class IncomeFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("this is Income form controller");
		
		int userId = Integer.parseInt(request.getParameter("incuserId"));
		String category = request.getParameter("incCategory");
		double amount = Double.parseDouble(request.getParameter("incAmount"));
		String receiveBy = request.getParameter("incReceiveBy");
		String date = request.getParameter("incDate");
		String remark = request.getParameter("incRemark");
		
		if(receiveBy.equals("cash")) {
			CashBookBean cb = new CashBookBean();
			cb.setTransaction_date(date);
			cb.setAmount(amount);
			cb.setUserid(userId);
			cb.setOperation("Receive");
			
			CashBookDAO cd = new CashBookDAO();
			cd.addCashBook(cb);
		}
		
		if(receiveBy.equals("check")) {
			BankBookBean bb = new BankBookBean();
			bb.setTransaction_date(date);
			bb.setAmount(amount);
			bb.setUserid(userId);
			bb.setOperation("Receive");
			
			BankBookDAO bd = new BankBookDAO();
			bd.addBankBook(bb);
		}
		
		
		/*find inc_catId using category Name*/
		Income_CategoryDAO icd = new Income_CategoryDAO();  
		int inc_catId = icd.findIdByICName(category);
		
		IncomesBean ib = new IncomesBean();
		ib.setUserid(userId);
		ib.setInc_ac(category);
		ib.setInc_catid(inc_catId);
		ib.setAmount(amount);
		ib.setTransaction_date(date);
		ib.setReceiveby(receiveBy);
		ib.setRemark(remark);	
		IncomesDAO id = new IncomesDAO(); 
		int r = id.addIncome(ib);
		
		if(r > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			session.setAttribute("r", r);
			out.println("<h1 style='color: green;'>Income Added Successfully.</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("IncomeForm");
			rd.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("IncomeForm");
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
