package databaseTest;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
	
		return "Results";
	}
	
	
	public static void main(String[] args) {
		BookDAOClass bog = new BookDAOClass();
		Book bog2 = new Book(51, "Bill", "Bob", "SHCMILL", 32, "GOUG", null, 0, false, null, "", " ");
		System.out.println(bog.addBook(bog2));
		List<Book> bog3 = bog.getAllBooks();
		//Book bog1 = bog.getBookbyAuthor("Brandon Sanderson");
	}
	
}
