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

/**
 * Servlet implementation class EncyclopediaServlet
 */
public class EncyclopediaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	connDB connect=new connDB();
	
	
	
    public EncyclopediaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String SpeciesID = request.getParameter("SpeciesID");
	
		
		if(SpeciesID!=null)
		{
		
		String outputStr = "";
		String selectSQL = "SELECT * FROM flower_dict WHERE flowerID=?";
		
		
		
		JSONObject flowerInfo =null;
	
		//String searchKey = inputParameter;
		try {
			flowerInfo = new JSONObject();
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,SpeciesID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					
					flowerInfo.put("flower_name", connect.rs.getString("flower_name"));
					flowerInfo.put("change_time", connect.rs.getString("change_time"));
					flowerInfo.put("ferttime", connect.rs.getString("ferttime"));
					flowerInfo.put("low_tem", connect.rs.getString("low_tem"));
					flowerInfo.put("high_tem", connect.rs.getString("high_tem"));
					flowerInfo.put("low_hum", connect.rs.getString("low_hum"));
					flowerInfo.put("high_hum", connect.rs.getString("high_hum"));
					flowerInfo.put("infoURL", connect.rs.getString("infoURL"));
					
					
					
					//outputStr=connect.rs.getString("flowID")+connect.rs.getString("tem")+connect.rs.getString("airhum")+connect.rs.getString("trigrt");
					
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		outputStr=flowerInfo.toString();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(outputStr);
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
			response.getWriter().write("Please Input the right parameter");
			
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
