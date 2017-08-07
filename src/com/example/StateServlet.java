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
@WebServlet("/StateServlet")
public class StateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StateServlet() {
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
		String selectSQL = "SELECT * FROM flowerstate WHERE userID=?";
		
		
		
		JSONObject state = new JSONObject();
	
		//String searchKey = inputParameter;
		try {
			
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					
						//state.put("flowID", connect.rs.getString("flowID"));
						state.put("tem", connect.rs.getString("tem"));
						state.put("airhum", connect.rs.getString("airhum"));
						state.put("soilhum", connect.rs.getString("soilhum"));
						//state.put("trigrt", connect.rs.getString("trigrt"));
						//state.put("starttime", connect.rs.getString("starttime"));
						//state.put("photo", connect.rs.getString("photo"));
					
					//outputStr=connect.rs.getString("flowID")+connect.rs.getString("tem")+connect.rs.getString("airhum")+connect.rs.getString("trigrt");
					
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		String selectSQL2 = "SELECT * FROM control WHERE userID=?";
		String magicNumber="";
		
		try {
		
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL2);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					
					magicNumber=connect.rs.getString("control");
					
					
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		System.out.println(magicNumber);
		System.out.println("magicSubstring:"+magicNumber.substring(1, 2));
		try {
		if(magicNumber.substring(1, 2).equals("0"))
		{
			
				state.put("nowstate","automatic");
			
		}
		else
		{
			state.put("nowstate","custom");
		}
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
				
		outputStr=state.toString();
		
		if(outputStr.equals("{}")!=true)
		{
			response.getWriter().write(outputStr);
		}	
		else
		{
			response.getWriter().write("can't not find this flower data");
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
