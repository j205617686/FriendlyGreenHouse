package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBtest
 */

public class DBtest extends HttpServlet {
	
	connDB connect=new connDB();


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String SpeciesID = request.getParameter("SpeciesID");
	
		
		String Str = "";
		String selectSQL = "SELECT * FROM flower_dict WHERE flowerID=?";
		
		
		//String searchKey = inputParameter;
		try {
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,SpeciesID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
			while(connect.rs.next()) 
			{
				
				Str=connect.rs.getString("tem");
				
				
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println(pStat);
		
		
		
			
		response.getWriter().write(Str);
			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
