package databaseTest;

public class UserBook extends Book {
private int userID;
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
private int currentPage;
private char status;
private int rating;
public UserBook(String title, int id, String author, String publisher, String genre, String coverURL, boolean released,
		int pageCount, int franchiseId, int seriesOrder, int seriesId, int userID, int currentPage, char status,
		int rating) {
	super(title, id, author, publisher, genre, coverURL, released, pageCount, franchiseId, seriesOrder, seriesId);
	this.userID = userID;
	this.currentPage = currentPage;
	this.status = status;
	this.rating = rating;
}
}
