package com.cognixia.jump.react.connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//This class uses externalized properties files
public class ConnectionManagerProperties {
	private static  String URL;
	private static  String USERNAME;
	private static  String PASSWORD;
	
	
	private static Connection connection = null;
	private static void makeConnection() {
		Properties props = new Properties();
		URL = props.getProperty("url");
		USERNAME =props.getProperty("username");
		PASSWORD = props.getProperty("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			props.load(new FileInputStream("/Users/macha/Documents/firstproject/ServletReact-IntegrationBranch /reactmaven/resources/config.properties"));
			URL = props.getProperty("url");
			USERNAME =props.getProperty("username");
			PASSWORD = props.getProperty("password");
			
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connetion made to :" + URL + "\nAs User:" +USERNAME);
			
		}catch(IOException e){
			e.printStackTrace();
			
		} catch(SQLException e) {
			System.out.println("Could not make connection to DB please see Below message");
			e.printStackTrace();
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public static Connection getConnection() {
		if (connection == null) {
			makeConnection();
		}
		return connection;
	}
}
