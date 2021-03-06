package databaseTest;

public class Book {
	
	private String Title;
	private int bookid;
	private String Author;
	private String Publisher;
	private String Genre;
	private String coverURL;
	private String description;
	
	private boolean Released ;
	private int pageCount;
	private Integer franchiseId;
	private int seriesOrder;
	private Integer seriesId;
	public Book(int id, String title, String author, String publisher,int pageCount, String genre, Integer seriesId, int seriesOrder, boolean released,  Integer franchiseId, String coverURL, String description 
			   ) {
		
		Title = title;
		this.bookid = id;
		Author = author;
		Publisher = publisher;
		Genre = genre;
		this.coverURL = coverURL;
		Released = released;
		this.pageCount = pageCount;
		this.franchiseId = franchiseId;
		this.seriesOrder = seriesOrder;
		this.seriesId = seriesId;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Book [Title=" + Title + ", id=" + bookid + ", Author=" + Author + ", Publisher=" + Publisher + ", Genre="
				+ Genre + ", coverURL=" + coverURL + ", description=" + description + ", Released=" + Released
				+ ", pageCount=" + pageCount + ", franchiseId=" + franchiseId + ", seriesOrder=" + seriesOrder
				+ ", seriesId=" + seriesId + "]";
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getBookId() {
		return bookid;
	}
	public void setBookId(int id) {
		this.bookid = id;
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
		return seriesId;
	}
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Author == null) ? 0 : Author.hashCode());
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (Author == null) {
			if (other.Author != null)
				return false;
		} else if (!Author.equals(other.Author))
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
