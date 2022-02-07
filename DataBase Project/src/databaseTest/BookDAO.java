package databaseTest;

import java.util.List;

public interface BookDAO {
	List<Book> getAllBooks();
	//CRDUL
	float getAverageRating(int id);
	Book getBookbyId(int id);
	Book getBookbyTitle(String name);
	Book getBookbyAuthor(String name);
	boolean addBook(Book book);
	boolean deleteBookbyId(int id);
	
	boolean updateBook(Book book);
	
	
	
	/*
	 * getallBooks
	 * getBookbyId()
	 * get 
	 */
	
	

}
