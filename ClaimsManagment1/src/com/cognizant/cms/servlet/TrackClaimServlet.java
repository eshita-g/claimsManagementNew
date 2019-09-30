package com.cognizant.cms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.cms.dao.ClaimDaoSql;
import com.cognizant.cms.dao.ConnectionHandler;
import com.cognizant.cms.model.Claim;

/**
 * Servlet implementation class TrackClaimServlet
 */
@WebServlet(name = "TrackClaim", urlPatterns = { "/TrackClaim" })
public class TrackClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackClaimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = ConnectionHandler.getConnection();
		 String id=request.getParameter("memid");
		 ClaimDaoSql claimDaoSql=new ClaimDaoSql();
			ArrayList<Claim> claimList = claimDaoSql.trackClaims();
			request.setAttribute("tclaims",claimList);
			RequestDispatcher rd = request.getRequestDispatcher("");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
