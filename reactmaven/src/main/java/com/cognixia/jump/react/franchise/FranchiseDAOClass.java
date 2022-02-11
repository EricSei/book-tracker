package com.cognixia.jump.react.franchise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.react.connection.ConnectionManagerProperties;

public class FranchiseDAOClass implements FranchiseDAO {

	public FranchiseDAOClass() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	@Override
	public List<Franchise> getAllFranchise() {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from franchises;"
					
					);
			rs = pstmt.executeQuery();
			
			List<Franchise> FranchisesList = new ArrayList<>();
			
			while(rs.next()) {
				Franchise Franchises = new Franchise(rs.getInt("franchiseID"), rs.getString("franName"));
				System.out.println(Franchises);
				FranchisesList.add(Franchises);
				
			}
			return FranchisesList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Franchise getFranchisebyId(int id) {
		// TODO Auto-generated method stub
				conn = ConnectionManagerProperties.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement(
							"select * from franchises where franchiseID = ?"
							
							);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					
					
					Franchise Franchises = null;
					while(rs.next()) {
						 Franchises = new Franchise(rs.getInt("franchiseID"), rs.getString("franName"));
						System.out.println(Franchises);
						
					}
					return Franchises;
					
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						pstmt.close();

						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
	@Override
	public Franchise getFranchisebyName(String name) {
	// TODO Auto-generated method stub
	conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement(
				"select * from franchises where franName = ?"
				
				);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		
		
		Franchise Franchises = null;
		while(rs.next()) {
			 Franchises = new Franchise(rs.getInt("franchiseID"), rs.getString("franName"));
			System.out.println(Franchises);
			
		}
		return Franchises;
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return null;
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
