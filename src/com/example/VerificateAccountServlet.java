package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.corba.se.spi.orbutil.fsm.State;

/**
 * Servlet implementation class StateServlet
 */

public class VerificateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
	    if(account!=null && password!=null)
	    {
		String outputStr = "";
		String selectSQL = "SELECT * FROM userid WHERE account=? && password=?";
		
		
		
		JSONObject state =null;
	
		//String searchKey = inputParameter;
		
		try {
			state = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,account);
			connect.pStat.setString(2,password);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					outputStr=connect.rs.getString("userID");
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		
			if(outputStr.equals(""))
			{
				response.getWriter().write("false");
				
			}
			else
			{
				response.getWriter().write(outputStr);
			}
			
	    }
	    else
	    {
	    	response.getWriter().write("Please Enter the right parameter");
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
