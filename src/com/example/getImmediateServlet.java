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

public class getImmediateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getImmediateServlet() {
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
		String selectSQL = "SELECT * FROM instant WHERE userID=?";
		
		
		
		JSONObject instantState =null;
	
		
		try {
			instantState = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					
					instantState.put("fan", connect.rs.getString("fan"));
					instantState.put("water", connect.rs.getString("water"));
					instantState.put("photo", connect.rs.getString("photo"));
					instantState.put("light", connect.rs.getString("light"));
						
					
					//outputStr=connect.rs.getString("flowID")+connect.rs.getString("tem")+connect.rs.getString("airhum")+connect.rs.getString("trigrt");
					
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		outputStr=instantState.toString();
		
		if(outputStr.equals("{}")!=true)
		{
			response.getWriter().write(outputStr);
		}	
		else
		{
			response.getWriter().write("can't not find this user's control data");
		}
		
		
		
			
		
		
		
		
		
		}
		else
		{
			
			response.getWriter().write("Please enter the right parameter");
			
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
