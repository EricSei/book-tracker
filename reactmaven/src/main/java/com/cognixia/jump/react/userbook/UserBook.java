package com.cognixia.jump.react.userbook;

import com.cognixia.jump.react.book.Book;

public class UserBook extends Book {
	private int userID;
	private int currentPage;
	private char status;
	private int rating;
	
	public UserBook(
			int id, String title, String author, String publisher, 
			int pageCount, String genre, int seriesId, int seriesOrder, 
			boolean released, int franchiseId, String coverURL, 
			String description, int userID, int currentPage, char status, 
			int rating) {
		
		super(id, title, author, publisher, pageCount, genre, seriesId, 
				seriesOrder, released, franchiseId, coverURL, description);
		
		this.userID = userID;
		this.currentPage = currentPage;
		this.status = status;
		this.rating = rating;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	


}
