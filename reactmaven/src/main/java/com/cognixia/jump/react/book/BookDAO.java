package com.cognixia.jump.react.book;

import java.util.List;

public interface BookDAO {
	List<Book> getAllBooks();
	
	Book getBookbyId(int id);
	Book getBookbyTitle(String name);
	Book getBookbyAuthor(String name);
	boolean addBook(Book book);
	boolean deleteBookbyId(int id);
	public Float getAverageRating(int id);
	boolean updateBook(Book book);
	

}
