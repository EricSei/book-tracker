package com.cognixia.jump.react.series;
import java.util.List;

import com.cognixia.jump.react.book.Book;

public class Series {

	List<Book> BooksInSeries;
	private int seriesID;
	private int serLength;
	private String seriesName;
	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	private boolean finished;
	private int franchiseID;
	public Series(List<Book> series, int seriesID, int serLength, boolean finished, int franchiseID) {
		
		BooksInSeries = null;
		this.seriesID = seriesID;
		this.serLength = serLength;
		this.finished = finished;
		this.franchiseID = franchiseID;
	}
	
public Series( int seriesID, String seriesName, int serLength, boolean finished, int franchiseID) {
		
		BooksInSeries = null;
		this.seriesID = seriesID;
		this.seriesName =seriesName;
		this.serLength = serLength;
		this.finished = finished;
		this.franchiseID = franchiseID;
	}
	@Override
	public String toString() {
		return "Series [Series=" + BooksInSeries + ", seriesID=" + seriesID + ", serLength=" + serLength + ", finished="
				+ finished + ", franchiseID=" + franchiseID + "]";
	}
	public List<Book> getSeries() {
		return BooksInSeries;
	}
	public void setSeries(List<Book> series) {
		BooksInSeries = series;
	}
	public int getSeriesID() {
		return seriesID;
	}
	public void setSeriesID(int seriesID) {
		this.seriesID = seriesID;
	}
	public int getSerLength() {
		return serLength;
	}
	public void setSerLength(int serLength) {
		this.serLength = serLength;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public int getFranchiseID() {
		return franchiseID;
	}
	public void setFranchiseID(int franchiseID) {
		this.franchiseID = franchiseID;
	}
	

}
