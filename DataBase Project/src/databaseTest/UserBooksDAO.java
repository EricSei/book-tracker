package databaseTest;

import java.util.List;

public interface UserBooksDAO {

		List<UserBook> getUserReadingList();
		String getUserBookReport(int id);
		String getUserReport(int id);
		UserBook getUserBookbyId(int id);
		UserBook getUSerBookbyTitle(String name);
		UserBook getUserBookbyAuthor(String name);
		boolean addUSerBook(UserBook book);
		boolean deleteUserBookbyId(int id);
		boolean deleteUserBook(UserBook book);
		boolean updateUserBook(UserBook book);
	
}
