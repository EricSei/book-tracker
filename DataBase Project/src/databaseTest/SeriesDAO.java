package databaseTest;

import java.util.List;

public interface SeriesDAO {

	List<Series> getAllSeries();

	Series getSeriesbyId(int id);
	Series getSeriesbyName(String name);
	boolean addSeries(Series series);
	boolean deleteSeriesbyId(int id);
	boolean deleteSeries(Series series);
	boolean updateSeries(Series series);
}
