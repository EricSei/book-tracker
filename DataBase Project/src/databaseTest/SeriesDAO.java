package databaseTest;

import java.util.List;

public interface SeriesDAO {

	List<Series> getAllSeries();

	Series getSeriesbyId(int id);
	Series getSeriesbyName(String name);
	boolean addSeries(Series book);
	boolean deleteSeriesbyId(int id);
	boolean deleteSeries(Series book);
	boolean updateSeries(Series book);
}
