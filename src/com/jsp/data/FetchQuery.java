package com.jsp.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class FetchQuery {

	public static void main(String[] args) {
		FileReader filereader = null;
		Properties properties = new Properties();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the employee id");
		int empid = scan.nextInt();
		String query = "select empname,email,phone from emp where empid=?";

		try {
			filereader = new FileReader("config/dbconfig.properties");
			properties.load(filereader);
			
			Class.forName(properties.getProperty("Driver"));
			System.out.println("load and register");
			
			connection = DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("username"),
					properties.getProperty("password"));
			System.out.println("Established the connection");
			
			//creation of preparedstatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, empid);
		
			resultset = preparedStatement.executeQuery();
			if(resultset.next())
			{
				System.out.println(resultset.getString(1));
				System.out.println(resultset.getString(2));
				System.out.println(resultset.getLong(3));
			}
			else
			{
				System.out.println("not found");
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//clean up code-->close-->statement,prepared stmt,connecion,result set
			if(preparedStatement !=null)
			{
				try {
					preparedStatement.close();//it will hold the object, not holding the null value;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}


}
