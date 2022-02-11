package com.cognixia.jump.react.user;
import java.util.List;

import com.cognixia.jump.react.userbook.UserBook;

public class User {
	private int userID;
	private String username;
	private String password;
	private List<UserBook> readingList;

	public User(int userId, String username, String password, List<UserBook> readingList) {

		this.userID = userId;
		this.username = username;
		this.password = password;
		this.readingList = readingList;
	}
	public User(int userId, String username, String password) {

		this.userID = userId;
		this.username = username;
		this.password = password;
		this.readingList = null;
	}


	public int getUserId() {
		return userID;
	}
	public void setUserId(int userId) {
		this.userID = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserBook> getReadingList() {
		return readingList;
	}
	public void addReadingList(UserBook book) {
		readingList.add(book);

	}
	public void removeReadingList(UserBook book) {
		readingList.add(book);

	}

	@Override
	public String toString() {
		return "User [userId=" + userID + ", username=" + username + ", password=" + password + ", readingList="
				+ readingList + "]";
	}

}
