package databaseTest;

public class Book {
	
	private String Title;
	private int id;
	private String Author;
	private String Publisher;
	private String Genre;
	private String coverURL;
	private boolean Released ;
	private int pageCount;
	private int franchiseId;
	private int seriesOrder;
	private int seriesId;
	public Book(String title, int id, String author, String publisher, String genre, String coverURL, boolean released,
			int pageCount, int franchiseId, int seriesOrder, int seriesId) {
		
		Title = title;
		this.id = id;
		Author = author;
		Publisher = publisher;
		Genre = genre;
		this.coverURL = coverURL;
		Released = released;
		this.pageCount = pageCount;
		this.franchiseId = franchiseId;
		this.seriesOrder = seriesOrder;
		this.seriesId = seriesId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	public boolean isReleased() {
		return Released;
	}
	public void setReleased(boolean released) {
		Released = released;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getFranchiseId() {
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
	public int getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	

}
