package com.cognixia.jump.react.userbook;
import java.util.List;

public interface UserBooksDAO {

		List<UserBook> getUserReadingList();
		List<UserBook>  getUserBookbyId(int Userid);
		UserBook getUserBookbyId(int Userid, int BookID);

		boolean addUserBook(UserBook book);
		boolean deleteUserBookbyId(UserBook book);

		boolean updateUserBook(UserBook book);
	
}
