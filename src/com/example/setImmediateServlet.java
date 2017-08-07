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

public class setImmediateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setImmediateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userID=request.getParameter("userID");
		String TAG=request.getParameter("TAG");
		String immParameter=request.getParameter("immParameter");
		
		System.out.println("§Y®É action");
		if(userID!=null && TAG!=null && immParameter!=null)
		{
		
			String getNowControl="SELECT control from control WHERE userID=?";
			String nowControlNumber = "";
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(getNowControl);
				
				connect.pStat.setString(1,userID);
				connect.rs=connect.pStat.executeQuery();
				
				while(connect.rs.next()) 
				{
					nowControlNumber=connect.rs.getString("control");
				}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} 
			
			System.out.println(nowControlNumber);
			
			String NewControlNum="1"+nowControlNumber.substring(1, 3);
			
			System.out.println(NewControlNum);
			
			String changeControl="UPDATE control SET control=? WHERE userID=?";
			
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(changeControl);
				connect.pStat.setString(1,NewControlNum);
				connect.pStat.setString(2,userID);
				connect.pStat.executeUpdate();
				
				
				response.getWriter().write("{\"status\":\"OK\",\"operation\":\"change control to Immediate\"}");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write(e.toString());
					
				} 
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		if(TAG.equals("0"))
		{
			String sqlcommand="UPDATE instant SET fan=1 WHERE userID=?";
			
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(sqlcommand);
				
				connect.pStat.setString(1,userID);
				connect.pStat.executeUpdate();
				
				
				response.getWriter().write("{\"status\":\"OK\",\"operation\":\"Increase Temperature\"}");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write(e.toString());
					
				} 
			
			
			
			
		}
		else if(TAG.equals("1"))
		{
			String sqlcommand="UPDATE instant SET fan=0 WHERE userID=?";
			
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(sqlcommand);
				
				connect.pStat.setString(1,userID);
				connect.pStat.executeUpdate();
				
				
				response.getWriter().write("{\"status\":\"OK\",\"operation\":\"Decrease Temperature\"}");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write(e.toString());
					
				} 
			
			
			
		}
		else if(TAG.equals("2"))
		{
			String sqlcommand="UPDATE instant SET water=1 WHERE userID=?";
			
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(sqlcommand);
				
				connect.pStat.setString(1,userID);
				connect.pStat.executeUpdate();
				
				
				response.getWriter().write("{\"status\":\"OK\",\"operation\":\"water flowers\"}");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write(e.toString());
					
				} 
			
			
			
		}
		else if(TAG.equals("3"))
		{
			String sqlcommand="UPDATE instant SET photo=1 WHERE userID=?";
			
			try {
				
				connect.pStat = connect.dbConnection.prepareStatement(sqlcommand);
				
				connect.pStat.setString(1,userID);
				connect.pStat.executeUpdate();
				
				
				response.getWriter().write("{\"status\":\"OK\",\"operation\":\"take a picture\"}");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().write(e.toString());
					
				} 
			
			
			
		}
		else
		{
			response.getWriter().write("Please Enter the right TAG");
			
		}
		
		
		
		
		
		}
		else
		{
			response.getWriter().write("Please Enter the parameter");
			
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
