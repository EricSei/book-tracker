package databaseTest;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class generalizedSQLMethods{
//This class will either hold all the methods that will generalized to work and do all the database operations
	/*
	 * 
	 * WE need to create methods that will be generalized to do these functions 
CRUDL
CREATE
READ
UPDATE
DELETE
LIST

	 */
	
	public static String SQLCreate(String table, String[] doug )  {
	//	int count = stmt.executeUpdate(
		//		"insert into " + table +" values(" + doug[0] + ", " + 'street', 'city','TX', '12345')");
		/*
		 * 	stmt =  conn.createStatement();
			
			int count = stmt.executeUpdate(
					"insert into address values(null, 'street', 'city','TX', '12345')");
					
			System.out.println("Rows inserted: " + count);
			
				rs = stmt.executeQuery(
						"select * from address;"
						);
		 */
		
		return "Results";
	}
	
}
