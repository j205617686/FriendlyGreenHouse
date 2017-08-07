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

public class getAutomaticStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAutomaticStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String userID = request.getParameter("userID");
	
		
		if(userID!=null)
		{
		
		String outputStr = "";
		String selectSQL = "SELECT * FROM control WHERE userID=?";
		
		
		
		JSONObject AutomaticState =null;
	
		String MagicStateNumber="";
		try {
			AutomaticState = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					MagicStateNumber=connect.rs.getString("control");
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			
		if(MagicStateNumber.equals("")!=true)
		{
		
		try {	
			/*
			if(MagicStateNumber.substring(0, 1).equals("0"))
			{
				AutomaticState.put("immediate", "false");
			}
			else if(MagicStateNumber.substring(0, 1).equals("1"))
			{
				AutomaticState.put("immediate", "true");
			}
			else
			{
				
			}
			*/
			if(MagicStateNumber.substring(1, 2).equals("0"))
			{
				AutomaticState.put("custom", "false");
			}
			else if(MagicStateNumber.substring(1, 2).equals("1"))
			{
				AutomaticState.put("custom", "true");
			}
			else
			{
				
			}
			if(MagicStateNumber.substring(2, 3).equals("0"))
			{
				AutomaticState.put("automatic", "false");
			}
			else if(MagicStateNumber.substring(2, 3).equals("1"))
			{
				AutomaticState.put("automatic", "true");
			}
			else
			{
				
			}
			
			
			
			/*
			if(MagicStateNumber.substring(1, 2).equals("0"))
			{
				response.getWriter().write("automatic");
			}
			else if(MagicStateNumber.substring(1, 2).equals("1"))
			{
				response.getWriter().write("custom");
			}
			*/
			response.getWriter().write(AutomaticState.toString());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
		}
		else
		{
			response.getWriter().write("can't not find the user");
			
		}
		
		
			
		
		
		
		}
		else
		{
			response.getWriter().write("Please Enter userID parameter");
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
