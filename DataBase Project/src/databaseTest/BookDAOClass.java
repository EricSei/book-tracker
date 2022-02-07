package databaseTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;





public class BookDAOClass implements BookDAO {

	public BookDAOClass() {
		// TODO Auto-generated constructor stub
	}
	private Connection conn = null;
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from books"
					);
			rs = pstmt.executeQuery();
			
			List<Book> bookList = new ArrayList<>();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
						rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
						rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
						rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description")
						);
				System.out.println(book);
				bookList.add(book);
				
			}
			return bookList;
			
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
	public float getAverageRating(int id) {
		return 0;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getBookbyId(int id) {
		conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement(
				"select * from books "+
		" where bookID = ?"
				);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		
	
		Book book = null;
		while(rs.next()) {
			 book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
					rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
					rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
					rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description")
					);
			//System.out.println(book);
		
			
		}
		return book;
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			pstmt.close();
	//		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return null;
}

	@Override
	public Book getBookbyTitle(String name) {
		conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement(
				"select * from books "+
		" where title = ?"
				);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		
	
		Book book = null;
		while(rs.next()) {
			 book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
					rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
					rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
					rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description")
					);
			System.out.println(book);
		
			
		}
		return book;
		
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
	public List<Book> getBookbyAuthor(String name) {
		conn = ConnectionManagerProperties.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		pstmt = conn.prepareStatement(
				"select * from books "+
		" where authorName = ?"
				);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		
		List<Book> bookList = new ArrayList<>();
		Book book = null;
		while(rs.next()) {
			 book = new Book(rs.getInt("bookID"), rs.getString("title"), rs.getString("authorName"),
					rs.getString("publisher"), rs.getInt("pageCount"),rs.getString("genre"),
					rs.getInt("seriesID"), rs.getInt("seriesOrder"), rs.getBoolean("released"),
					rs.getInt("franchiseID"), rs.getString("coverURL"), rs.getString("description")
					);
			System.out.println(book);
			bookList.add(book);
		
			
		}
		return bookList;
		
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
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
	
		int query = 0;
		
		try {
			pstmt = conn.prepareStatement(
					"insert into books(bookID, title, authorName, publisher, pageCount, genre, seriesID, seriesOrder, released, franchiseID, coverURL, description) "
					+ "values(?, ?, ?,?,?,?,?,?,?,?,?,?)"
					);
			pstmt.setInt(1, 0);
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setInt(5, book.getPageCount());
			pstmt.setString(6, book.getGenre());
			if(book.getSeriesId() == null) {
			pstmt.setNull(7, Types.INTEGER);	
			}else {
			pstmt.setInt(7, book.getSeriesId());
			}
			pstmt.setInt(8, book.getSeriesOrder());
			pstmt.setBoolean(9, book.isReleased());
			
			if(book.getFranchiseId() == null) {
				pstmt.setNull(10, Types.INTEGER);	
				}else {
					pstmt.setInt(10, book.getFranchiseId());
				}
			pstmt.setString( 11, book.getCoverURL());
			pstmt.setString(12, book.getDescription());
			
			
			
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

	@Override
	public boolean deleteBookbyId(int id) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		boolean success = false;
		
		try {
			
			if (id != -1 ) {
				pstmt = conn.prepareStatement(
						"DELETE FROM books WHERE bookID = ?"
						);
				pstmt.setInt(1, id);
				success = pstmt.execute();
				System.out.println("AAAA" + success);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
		
	}

	public int getDBSize() {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int retVal;
		try {
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM books");
			rs = pstmt.executeQuery();
			rs.next();
			retVal = rs.getInt(1);
			return retVal;
			
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
		return 0;
		
	}
	
	public boolean deleteBookbyTitle(String title) {
		conn = ConnectionManagerProperties.getConnection();
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		boolean success = false;
		
		try {
			
			if (title != "" ) {
				pstmt = conn.prepareStatement(
						"DELETE FROM books WHERE title = ?"
						);
				pstmt.setString(1, title);
				success = pstmt.execute();
				System.out.println("AAAA" + success);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//rs.close();
				pstmt.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
		
	}

	@Override
	public boolean updateBook(Book book) {
		 {

				String title = book.getTitle();
				Integer pageCount = book.getPageCount();
				int id = -1;
				
				conn = ConnectionManagerProperties.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				boolean success = false;
				
				try {
					pstmt = conn.prepareStatement(
							"select * from books "
							+ "where Title = ? and pageCount = ?"
							);
					
					pstmt.setString(1, title);
					pstmt.setInt(2, pageCount);
					
					rs = pstmt.executeQuery();
					rs.first();
					id = rs.getInt("bookID");
					
					if (id != -1 ) {
						pstmt = conn.prepareStatement(
								"update books set bookID = ?, set title = ?, set authorName = ?, set publisher = ?, set pageCount = ?, set genre = ?, set seriesID = ?, set seriesOrder = ?, set released = ?, set franchiseID = ?, set coverURL = ?, set description = ?"
								
								+ " where bookID = ?"
								
					);
						pstmt.setInt(1, 0);
						pstmt.setString(2, book.getTitle());
						pstmt.setString(3, book.getAuthor());
						pstmt.setString(4, book.getPublisher());
						pstmt.setInt(5, book.getPageCount());
						pstmt.setString(6, book.getGenre());
						if(book.getSeriesId() == null) {
						pstmt.setNull(7, Types.INTEGER);	
						}else {
						pstmt.setInt(7, book.getSeriesId());
						}
						pstmt.setInt(8, book.getSeriesOrder());
						pstmt.setBoolean(9, book.isReleased());
						
						if(book.getFranchiseId() == null) {
							pstmt.setNull(10, Types.INTEGER);	
							}else {
								pstmt.setInt(10, book.getFranchiseId());
							}
						pstmt.setString( 11, book.getCoverURL());
						pstmt.setString(12, book.getDescription());
						success = pstmt.execute();
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return success;
			}
	
	}

}
