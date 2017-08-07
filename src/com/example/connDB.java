package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connDB {
	

	private static final long serialVersionUID = 1L;
    
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://140.121.150.53/flowstate?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "asdasdasd";
	
	Connection dbConnection = null;
	PreparedStatement pStat = null;
	String selectSQL = null;
	ResultSet rs = null;
	
	
	public connDB()
	{
		try
		{
			Class.forName(DB_DRIVER);	 
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException:"+e.toString());
		}
 
		try
		{	 
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
		}
		catch (SQLException e)
		{
			System.out.println("SQLException DATABASE±b¸¹±K½X¿é¤J¿ù»~:"+e.toString());
			
		}	 
		
		
			
		
	}
	

	
	
	
	
	
}
