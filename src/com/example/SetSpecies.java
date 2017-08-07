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

public class SetSpecies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	connDB connect=new connDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetSpecies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String SpeciesID = request.getParameter("SpeciesID");
		String userID = request.getParameter("userID");
		
		
		String outputStr = "";
		String updatesql = "UPDATE flowerstate SET flowID=? WHERE userID=?";
		
		//System.out.println(SpeciesID);
		
		if(SpeciesID!=null &&userID!=null)
		{
		
		
		try {
			
			connect.pStat = connect.dbConnection.prepareStatement(updatesql);
			connect.pStat.setString(1,SpeciesID);
			connect.pStat.setString(2,userID);
			connect.pStat.executeUpdate();
			
			
			response.getWriter().write("{\"status\":\"OK\",\"operation\":\"change flower\"}");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.toString());
				
			} 
				
		
		
		}
		else
		{
			response.getWriter().write("please give parameter");
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
