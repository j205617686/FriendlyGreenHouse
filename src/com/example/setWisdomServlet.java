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


public class setWisdomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setWisdomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String userID = request.getParameter("userID");
		String custom = request.getParameter("custom");
		String automatic = "";
		
		if(custom!=null && custom!=null)
		{
		
	
		
		
		if(custom.equals("0") || custom.equals("1"))
		{
		
			if(custom.equals("0"))
				automatic="1";
			else if(custom.equals("1"))
				automatic="0";
			
			
			
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
				
		
		
		String changeMagicNumber=MagicStateNumber.substring(0,1)+custom+automatic;
		
		
		String selectSQL2 ="UPDATE control SET control=? WHERE userID=?";
		
		try {
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL2);
			connect.pStat.setString(1,changeMagicNumber);
			connect.pStat.setString(2,userID);
			connect.pStat.executeUpdate();
			
			
			
			response.getWriter().write("{\"status\":\"OK\",\"operation\":\"custom="+custom+"\"}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.toString());
		}
		
		
		
		
		
		}
		else
		{
			
			response.getWriter().write("Please Input correct set Number");
		}
			
	
		}
		else
		{
			response.getWriter().write("Please Input correct parameter");
			
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
