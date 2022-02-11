package com.cognixia.jump.react.userbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.react.connection.ConnectionManagerProperties;

public class UserBooksDAOClass implements UserBooksDAO {

	private Connection conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public List<UserBook> getUserReadingList() {

		try{
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
		}
		
		return null;
	}


	@Override
	public List<UserBook> getUserBookbyId(int Userid) {

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
		}
		return null;
	}

	@Override
	public UserBook getUserBookbyId(int Userid, int BookID) {
		try{
			pstmt = conn.prepareStatement(
					"select * from books"
							+ " inner JOIN userbooks on books.bookID=userbooks.bookID  where userID = ? AND where bookID = ? "
					);
			pstmt.setInt(1, Userid);
			pstmt.setInt(2, BookID);
			rs = pstmt.executeQuery();

			UserBook userbook= null;
			while(rs.next()) {
				userbook = new UserBook(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
						rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
						rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
						rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description"), rs.getInt("userID"), rs.getInt("currentPage"),
						rs.getString("currStatus").charAt(0), rs.getInt("rating")
						);
				System.out.println(userbook);

			}
			return userbook;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public boolean addUserBook(UserBook book) {

		try{
			pstmt = conn.prepareStatement(
					"insert into userbooks(userID, bookID, currentPage, currStatus, rating) "
							+ "values(?,?,?,?,?)"
					);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, book.getBookId());
			pstmt.setInt(3, book.getCurrentPage());
			pstmt.setString(4, String.valueOf(book.getStatus()));
			pstmt.setInt(5, book.getRating());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean deleteUserBookbyId(UserBook Userbook) {

		boolean success = false;

		try{

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
		}

		return success;

	}


	@Override
	public boolean updateUserBook(UserBook book) {
		
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
		} 

		return success;

	}

}
