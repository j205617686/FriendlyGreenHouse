package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
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
@WebServlet("/getPhotoServlet")
public class getPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	connDB connect=new connDB();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String userID=request.getParameter("userID");
		
		
		
		
		String outputStr = "";
		String selectSQL = "SELECT * FROM flowerstate WHERE userID=?";
		
		String photoEncodingStr="";
	
		//String searchKey = inputParameter;
		try {
			
			connect.pStat = connect.dbConnection.prepareStatement(selectSQL);
			connect.pStat.setString(1,userID);
			connect.rs = connect.pStat.executeQuery();
			
			
			
				while(connect.rs.next()) 
				{
					
					Blob b = connect.rs.getBlob("photo");
					long size = b.length();
					byte[] bs = b.getBytes(1, (int)size);
					response.setContentType("image/jpeg"); 
					OutputStream outs = response.getOutputStream(); 
					outs.write(bs);
					outs.flush();
					
					
					
					
					//photoEncodingStr=connect.rs.getString("photo");
					
				}
				connect.rs.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		//response.setContentType("text/html; charset=UTF-8");
		//response.setCharacterEncoding("UTF-8");
		
		
	
		
			//response.getWriter().write(photoEncodingStr);
	
		
		
		
	
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
