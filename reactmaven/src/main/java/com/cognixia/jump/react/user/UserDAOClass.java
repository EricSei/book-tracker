package com.cognixia.jump.react.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.react.connection.ConnectionManagerProperties;

public class UserDAOClass implements UserDAO {
	
	private Connection conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public List<User> getAllUsers() {

		try{
			pstmt = conn.prepareStatement(
					"select * from users"
					);
			rs = pstmt.executeQuery();

			List<User> UserList = new ArrayList<>();
			while(rs.next()) {
				User User = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"));
				UserList.add(User);
			}
			System.out.println(UserList);
			return UserList;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(int id) {

		try{
			pstmt = conn.prepareStatement(
					"select * from users where userID = ?"
					);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			User user = null;
			while(rs.next()) {
				user = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"));
			}
			return user;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserbyUsername(String Username) {

		try {
			pstmt = conn.prepareStatement(
					"select * from users where username = ?"
					);
			pstmt.setString(1, Username);
			rs = pstmt.executeQuery();
			
			User user = null;
			while(rs.next()) {
				user = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"));
				System.out.println(user);

			}
			return user;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean authenticateUser(String Username, String password) {

		try{
			pstmt = conn.prepareStatement(
					"select * from users where username = ? and password = ?"
					);
			pstmt.setString(1, Username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {

		int query = 0;

		try{
			pstmt = conn.prepareStatement(
					"insert into users(userID, username, password)"
							+ "values(?, ?, ?)"
					);
			pstmt.setInt(1, 0);
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());


			query = pstmt.executeUpdate();

			System.out.println(query);


		} catch (SQLException e) {
			e.printStackTrace();
		} 
		if(query > 0) return true;

		return false;

	}

	@Override
	public boolean deleteUserbyId(int id) {

		boolean success = false;

		try{

			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"delete from users"
								+ "where userID = ?"
						);
				pstmt.setInt(1, id);
				success = pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;

	}

	@Override
	public boolean deleteUserbyUsernaem(String username) {

		boolean success = false;
		int id;
		try{
			pstmt = conn.prepareStatement(
					"select * from users "
							+ "where username = ?"
					);

			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			rs.next();
			id = rs.getInt("userID");

			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"delete from users "
								+ "where userID = ?"
						);
				pstmt.setInt(1, id);
				success = pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;

	}

	@Override
	public boolean updateuser(User user) {
		int id = user.getUserId();

		boolean success = false;

		try{

			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"update users set username = ?, set password = ? where userID = ?");

				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setInt(3, id);

				success = pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
	
}


