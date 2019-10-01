package com.cognizant.cms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.cms.dao.ClaimDaoSql;
import com.cognizant.cms.model.Claim;

/**
 * Servlet implementation class PreviousClaimServlet
 */
@WebServlet(name = "PreviousClaim", urlPatterns = { "/PreviousClaim" })
public class PreviousClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviousClaimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("memid");
		ClaimDaoSql claimdaosql = new ClaimDaoSql();
		ArrayList<Claim> claimlist = claimdaosql.getPreviousClaim(id);
		request.setAttribute("prevclaims", claimlist);
		RequestDispatcher rd = request.getRequestDispatcher("/previous_claims.jsp");
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
