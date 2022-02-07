package databaseTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDAOClass implements UserDAO {

	public UserDAOClass() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from users"
					);
			rs = pstmt.executeQuery();
			
			List<User> UserList = new ArrayList<>();
			List<UserBook> readingList = null;
			while(rs.next()) {
				User User = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"),  readingList);
				System.out.println(User);
				UserList.add(User);
				
			}
			return UserList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

			//	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from users where userID = ?"
					);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
		
			List<UserBook> readingList = null;
			User user = null;
			while(rs.next()) {
				 user = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"),  readingList);
				System.out.println(user);
			
				
			}
			return user;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

			//	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User getUserbyUsername(String Username) {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from users where username = ?"
					);
			pstmt.setString(1, Username);
			rs = pstmt.executeQuery();
			
		
			List<UserBook> readingList = null;
			User user = null;
			while(rs.next()) {
				 user = new User(rs.getInt("userID") , rs.getString("username"), rs.getString("password"),  readingList);
				System.out.println(user);
			
				
			}
			return user;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean authenticateUser(String Username, String password) { //HORRRIBLE FIX THIS LATER!!!!!
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from users where username = ?"
					);
			pstmt.setString(1, Username);
			rs = pstmt.executeQuery();
			
		
			List<UserBook> readingList = null;
			User user = null;
			while(rs.next()) {
				if(rs.getString("password").equals(password)) {
					return true;
				}else {
					return false;
				}
				
				
			
				
			}
	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();

			//	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null != null;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub

		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
	
		int query = 0;
		
		try {
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
		} finally {
			
			try {
				pstmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if(query > 0) return true;
		
		return false;
		
	}

	@Override
	public boolean deleteUserbyId(int id) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			
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
	public boolean deleteUserbyUsernaem(String username) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		int id;
		try {
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
	public boolean updateuser(User user) {
		 {
				int id = user.getUserId();
				
				conn = ConnectionManagerProperties.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				boolean success = false;
				
				try {
					
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
				} finally {
					try {
						rs.close();
						pstmt.close();
//						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return success;
			}
	
	}
	}


