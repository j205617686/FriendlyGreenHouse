package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.corba.se.spi.orbutil.fsm.State;

/**
 * Servlet implementation class StateServlet
 */
@WebServlet("/allEncyclopedia")
public class AllEncyclopediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllEncyclopediaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
		String outputStr = "";
		String selectSQL = "SELECT * FROM flower_dict WHERE 1";
		
		JSONArray allflowerArray=new JSONArray();
		
		JSONObject state =null;
	
		//String searchKey = inputParameter;
		try {
			state = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					state = new JSONObject();
					state.put("flowerID", connect.rs.getString("flowerID"));
					state.put("flower_name", connect.rs.getString("flower_name"));
					state.put("change_time", connect.rs.getString("change_time"));
					state.put("ferttime", connect.rs.getString("ferttime"));
					state.put("low_tem", connect.rs.getString("low_tem"));
					state.put("high_tem", connect.rs.getString("high_tem"));
					state.put("low_hum", connect.rs.getString("low_hum"));
					state.put("high_hum", connect.rs.getString("high_hum"));
					state.put("infoURL", connect.rs.getString("infoURL"));
					
					allflowerArray.put(state);
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		outputStr=allflowerArray.toString();
		if(outputStr.equals("{}")!=true)
		{
			response.getWriter().write(outputStr);
		
			
			
		}
		else
		{
		
			response.getWriter().write("no data in flower dictionary");
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
