package com.louishong.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class SQliteExample
{
	public static String sDriver = "org.sqlite.JDBC";
	public static String sUrl = "jdbc:sqlite:/Users/honglouis/Documents/ServerWorkspace/Gravifile/src/com/louishong/database/Profiles.sqlite";

	public static void main(String arg[])
	{
		//Setup Driver #1
		try
		{
			Driver d = (Driver)Class.forName(sDriver).newInstance();
			DriverManager.registerDriver(d);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//Create Connection
		Connection con;
		try
		{
			con = DriverManager.getConnection(sUrl);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		//Execute SQL Query using Statement object
		Statement stmt;
		ResultSet result;
		
		try{
			String sql = "SELECT FirstName, LastName, Email FROM Profiles ORDER BY LastName";
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
			try
			{
				con.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			return;
		}
		
		//Read results #4
		try
		{
			String fName, lName, email;
			while (result.next())
			{
				email = result.getString("Email");
				fName = result.getString("FirstName");
				lName = result.getString("LastName");
				System.out.println(fName + " " + lName + " | " + email);
			}
		}catch(SQLException e)
		{
            try
            {
                result.close();
                stmt.close();
                con.close();
            }
            catch(Exception  ex)
            {
            }
            return;
		}
		
	}
}