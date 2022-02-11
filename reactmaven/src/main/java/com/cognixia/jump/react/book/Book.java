package com.cognixia.jump.react.book;

public class Book {

	private int bookID;
	private String title;
	private String authorName;
	private String publisher;
	private String genre;
	private String coverURL;
	private String description;
	private boolean released ;
	private int pageCount;
	private Integer franchiseId;
	private int seriesOrder;
	private Integer seriesID;

	public Book(
			int id, String title, String authorName, String publisher,
			int pageCount, String genre, Integer seriesId, int seriesOrder, 
			boolean released,  Integer franchiseId, String coverURL,
			String description) {

		this.bookID = id;
		this.title = title;
		this.authorName = authorName;
		this.publisher = publisher;
		this.genre = genre;	
		this.coverURL = coverURL;
		this.released = released;
		this.pageCount = pageCount;
		this.franchiseId = franchiseId;
		this.seriesOrder = seriesOrder;
		this.seriesID = seriesId;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookId() {
		return bookID;
	}
	public void setBookId(int id) {
		this.bookID = id;
	}
	public String getAuthor() {
		return authorName;
	}
	public void setAuthor(String author) {
		this.authorName = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	public boolean isReleased() {
		return released;
	}
	public void setReleased(boolean released) {
		this.released = released;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getFranchiseId() {
		return franchiseId;
	}
	public void setFranchiseId(int franchiseId) {
		this.franchiseId = franchiseId;
	}
	public int getSeriesOrder() {
		return seriesOrder;
	}
	public void setSeriesOrder(int seriesOrder) {
		this.seriesOrder = seriesOrder;
	}
	public Integer getSeriesId() {
		return seriesID;
	}
	public void setSeriesId(int seriesId) {
		this.seriesID = seriesId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [Title=" + title + ", id=" + bookID + ", Author=" + authorName + ", Publisher=" + publisher + ", Genre="
				+ genre + ", coverURL=" + coverURL + ", description=" + description + ", Released=" + released
				+ ", pageCount=" + pageCount + ", franchiseId=" + franchiseId + ", seriesOrder=" + seriesOrder
				+ ", seriesId=" + seriesID + "]";
	}

}
