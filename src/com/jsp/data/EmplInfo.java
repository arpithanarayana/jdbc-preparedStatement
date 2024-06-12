package com.jsp.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EmplInfo {

	public static void main(String[] args) {
		FileReader filereader = null;
		Properties properties = new Properties();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultset = null;
		String query =
				"select * from emp";
				//"insert into emp(empname,email,password,address,state,city,pincode,phone) values(?,?,?,?,?,?,?,?)";
				//"create table emp(empid integer auto_increment primary key,empname varchar(20),email varchar(20),password varchar(10),address varchar(20),state varchar(20),city varchar(15),pincode integer,phone bigint)";
		
		try {
			filereader = new FileReader("config/dbconfig.properties");
			properties.load(filereader);
			
			Class.forName(properties.getProperty("Driver"));
			System.out.println("load and register");
			
			connection = DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("username"),
					properties.getProperty("password"));
			System.out.println("Established the connection");
			
			//statement = connection.createStatement();
			
			//boolean res = statement.execute(query);
			//System.out.println(res);
			
			//creation of preparedstatement
            preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, "Veda");
//			preparedStatement.setString(2, "veda@789gmail.com");
//			preparedStatement.setString(3,"789ved");
//			preparedStatement.setString(4, "Nagamagala");
//			preparedStatement.setString(5, "Karnataka");
//			preparedStatement.setString(6, "Mandya");
//			preparedStatement.setInt(7, 570041);
//			preparedStatement.setLong(8, 9036121250L);
//			
//			int update = preparedStatement.executeUpdate();
//			System.out.println("No of rows affected : " + update);
//			
			resultset = preparedStatement.executeQuery();
			while(resultset.next())
			{
				System.out.println(resultset.getString(1));
				System.out.println(resultset.getString(2));
				System.out.println(resultset.getString(3));
				System.out.println(resultset.getString(4));
				System.out.println(resultset.getString(5));
				System.out.println(resultset.getString(6));
				System.out.println(resultset.getString(7));
				System.out.println(resultset.getInt(8));
				System.out.println(resultset.getLong(9));
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
