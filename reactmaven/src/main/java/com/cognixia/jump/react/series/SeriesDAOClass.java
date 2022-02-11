package com.cognixia.jump.react.series;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.react.connection.ConnectionManagerProperties;




public class SeriesDAOClass implements SeriesDAO {

	public SeriesDAOClass() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	@Override
	public List<Series> getAllSeries() {
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select * from department"
					);
			rs = pstmt.executeQuery();
			
			List<Series> seriesList = new ArrayList<>();
			
			while(rs.next()) {
				Series dept = new Series(
						rs.getInt("seriesID"), 
						rs.getString("seriesName"), 
						rs.getInt("serLength"),
						rs.getBoolean("finished"),
						rs.getInt("franchiseID"));
				
				seriesList.add(dept);
			}
			
			return seriesList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Series getSeriesbyId(int id) {
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select * from department" +
			" where seriesID = ?"
					);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			Series dept = null;
			while(rs.next()) {
				 dept = new Series(
						rs.getInt("seriesID"), 
						rs.getString("seriesName"), 
						rs.getInt("serLength"),
						rs.getBoolean("finished"),
						rs.getInt("franchiseID"));
				
				//seriesList.add(dept);
			}
			
			return dept;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public Series getSeriesbyName(String name) {
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(
					"select * from department" +
			" where seriesName = ?"
					);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			Series dept = null;
			while(rs.next()) {
				 dept = new Series(
						rs.getInt("seriesID"), 
						rs.getString("seriesName"), 
						rs.getInt("serLength"),
						rs.getBoolean("finished"),
						rs.getInt("franchiseID"));
				
				//seriesList.add(dept);
			}
			
			return dept;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public boolean addSeries(Series series)  {
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
	
		int query = 0;
		
		try {
			pstmt = conn.prepareStatement(
					"insert into series(seriesID, seriesName, serLength, finished, franchiseID) "
					+ "values(?, ?, ?, ?, ?)"
					);
			pstmt.setInt(1, 0);
			pstmt.setString(2, series.getSeriesName());
			pstmt.setInt(3, series.getSerLength());
			pstmt.setBoolean(3, series.isFinished());
			pstmt.setInt(3, series.getFranchiseID());
			
			query = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if(query > 0) return true;
		
		return false;
	}

	@Override
	public boolean deleteSeriesbyId(int id) {

	
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			
			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"delete from series "
						+ "where seriesID = ?"
						);
				pstmt.setInt(1, id);
				success = pstmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
		
	}

	@Override
	public boolean deleteSeries(Series series) {

	
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			pstmt = conn.prepareStatement(
					"select * from series "
					+ "where seriesName = ? and serLength = ?"
					);
			
			pstmt.setString(1, series.getSeriesName());
			pstmt.setInt(2, series.getSerLength());
			
			rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("seriesID");
			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"delete from series "
						+ "where seriesID = ?"
						);
				pstmt.setInt(1, series.getSeriesID());
				success = pstmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
		
	}

	@Override
	public boolean updateSeries(Series series) {

	
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			pstmt = conn.prepareStatement(
					"select * from series "
					+ "where seriesName = ?"
					);
			
			pstmt.setString(1, series.getSeriesName());
			
			rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("seriesID");
			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"update from series "+
							"set serLength =?, set finished = ?, set franchiseID = ? "
						+ "where seriesID = ?"
						);
				pstmt.setInt(1, series.getSerLength());
				pstmt.setBoolean(2, series.isFinished());
				pstmt.setInt(3, series.getFranchiseID());
				pstmt.setInt(4, series.getSeriesID());
				success = pstmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
		
	}
	
	@Override
	public void finalize(){
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
