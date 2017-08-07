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

public class setCustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setCustomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String userID = request.getParameter("userID");
		String lowTem = request.getParameter("lowTem");
		String highTem = request.getParameter("highTem");
		String lowHum = request.getParameter("lowHum");
		String highHum = request.getParameter("highHum");
	
		System.out.println("set Custom");
		String outputStr = "";
		String selectSQL = "UPDATE semi SET low_tem=?,high_tem=?,low_hum=?,high_hum=? WHERE userID=?";
		
		
		
		JSONObject state =null;
	
		if(lowTem!=null && highTem!=null && lowHum!=null && lowHum!=null && highHum!=null)
		{
		
		try {
			state = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,lowTem);
			connect.pStat.setString(2,highTem);
			connect.pStat.setString(3,lowHum);
			connect.pStat.setString(4,highHum);
			connect.pStat.setString(5,userID);
			connect.pStat.executeUpdate();
			
			
			response.getWriter().write("{\"status\":\"OK\",\"operation\":\"custom setting\"}");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.getWriter().write(e.toString());
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
