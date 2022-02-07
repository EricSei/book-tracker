package databaseTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserBooksDAOClass implements UserBooksDAO {

	public UserBooksDAOClass() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	
	@Override
	public List<UserBook> getUserReadingList() {
	// TODO Auto-generated method stub
	conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement(
				"select * from books"
				+ "inner JOIN userbooks on books.bookID=userbooks.bookID;"
				);
		rs = pstmt.executeQuery();
		
		List<UserBook> UserBookList = new ArrayList<>();
		
		while(rs.next()) {
			UserBook UserBook = new UserBook(rs.getInt("UserBookID"), rs.getString("title"), rs.getString("authorName"),
					rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
					rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
					rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description"), rs.getInt("userID"), rs.getInt("currentPage"),
					rs.getString("currStatus").charAt(0), rs.getInt("rating")
					);
			System.out.println(UserBook);
			UserBookList.add(UserBook);
			
		}
		return UserBookList;
		
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
	public String getUserBookReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<UserBook> getUserBookbyId(int Userid) {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from books"
					+ " inner JOIN userbooks on books.bookID=userbooks.bookID  where userID = ?"
					);
			pstmt.setInt(1, Userid);
			rs = pstmt.executeQuery();
			
			List<UserBook> UserBookList = new ArrayList<>();
			
			while(rs.next()) {
				UserBook UserBook = new UserBook(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
						rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
						rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
						rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description"), rs.getInt("userID"), rs.getInt("currentPage"),
						rs.getString("currStatus").charAt(0), rs.getInt("rating")
						);
				System.out.println(UserBook);
				UserBookList.add(UserBook);
				
			}
			return UserBookList;
			
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
	public UserBook getUserBookbyId(int Userid, int BookID) {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from books"
					+ " inner JOIN userbooks on books.bookID=userbooks.bookID  where userID = ? AND where bookID = ? "
					);
			pstmt.setInt(1, Userid);
			pstmt.setInt(2, BookID);
			rs = pstmt.executeQuery();
			
	//		List<UserBook> UserBookList = new ArrayList<>();
			UserBook userbook= null;
			while(rs.next()) {
				 userbook = new UserBook(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
						rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
						rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
						rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description"), rs.getInt("userID"), rs.getInt("currentPage"),
						rs.getString("currStatus").charAt(0), rs.getInt("rating")
						);
				System.out.println(userbook);
		//		UserBookList.add(UserBook);
				
			}
			return userbook;
			
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
	public boolean addUserBook(UserBook book) {{
		// TODO Auto-generated method stub

		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
	
		int query = 0;
		
		try {
			pstmt = conn.prepareStatement(
					"insert into userbooks(userID, bookID, currentPage, currStatus, rating) "
					+ "values(?,?,?,?,?)"
					);
			pstmt.setInt(1, book.getUserID());
			pstmt.setInt(2, book.getBookId());
			pstmt.setInt(3, book.getCurrentPage());
			pstmt.setString(4, String.valueOf(book.getStatus()));
			pstmt.setInt(5, book.getRating());
			
			
			
			query = pstmt.executeUpdate();
			
			//System.out.println(query);
			
			
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
	}

	@Override
	public boolean deleteUserBookbyId(UserBook Userbook) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			
			if ((Userbook.getUserID() != -1 )&& (Userbook.getBookId() != -1)) {
				pstmt = conn.prepareStatement(
						"delete from userbooks"
						+ "where bookID = ? and where userID = ?"
						);
				pstmt.setInt(1, Userbook.getUserID());
				pstmt.setInt(2, Userbook.getBookId());
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
	public boolean updateUserBook(UserBook book) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = false;
		
		try {
			pstmt = conn.prepareStatement(
					"update userbooks set bookID = ?, currentPage= ?, currStatus= ?, rating= ? + where userID = ?"
					+ "	values(?,?,?,?,?)"
					);
			
			
			pstmt.setInt(1, book.getBookId());
			pstmt.setInt(2, book.getCurrentPage());
			pstmt.setString(3, String.valueOf(book.getStatus()));
			pstmt.setInt(4, book.getRating());
			pstmt.setInt(5, book.getUserID());
			
			
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
	
}
