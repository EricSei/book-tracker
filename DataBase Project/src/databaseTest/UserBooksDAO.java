package databaseTest;

import java.util.List;

public interface UserBooksDAO {

		List<UserBook> getUserReadingList();
		String getUserBookReport(int id); //Extra Functions
		String getUserReport(int id); //ExtraFunction
		List<UserBook>  getUserBookbyId(int Userid);
		UserBook getUserBookbyId(int Userid, int BookID);
	//	UserBook getUSerBookbyTitle(String name);
		//UserBook getUserBookbyAuthor(String name);
		boolean addUserBook(UserBook book);
		boolean deleteUserBookbyId(UserBook book);
		//boolean deleteUserBook(UserBook book);
		boolean updateUserBook(UserBook book);
	
}
