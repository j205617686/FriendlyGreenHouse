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
 * Servlet implementation class GetSpecies
 */
@WebServlet("/GetSpecies")
public class GetSpecies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	connDB connect=new connDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpecies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userID = request.getParameter("userID");
	
		
		String outputStr = "";
		String selectFlowerID = "SELECT * FROM flowerstate WHERE userID=?";
		
		if(userID!=null)
		{
		String flowerID = "";
		try {
			
			connect.pStat = connect.dbConnection.prepareStatement(selectFlowerID);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					flowerID=connect.rs.getString("flowID");
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
		String selectFlowerName = "SELECT * FROM flower_dict WHERE flowerID=?";
		
		
		String flowerName = "";
		try {
			
			connect.pStat = connect.dbConnection.prepareStatement(selectFlowerName);
			connect.pStat.setString(1,flowerID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					flowerName=connect.rs.getString("flower_name");
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		

		
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
			if(flowerName.equals(""))
			{
				response.getWriter().write("false");
			}
			else
			{
				response.getWriter().write(flowerName);
			}
				
			
		}
		else
		{
			response.getWriter().write("Please input correct parameter");
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
