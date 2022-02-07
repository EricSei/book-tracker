package databaseTest;

import java.util.List;

public interface UserDAO {

	
	List<User> getAllUsers();
	User getUserById(int id);
	User getUserbyUsername(String Username);
	boolean authenticateUser(String Username, String password);
	boolean addUser(User user);
	boolean deleteUserbyId(int id);
	boolean deleteUserbyUsernaem(String username);
	boolean deleteUser(User user);
	boolean updateuser(User user);
	
	
}
