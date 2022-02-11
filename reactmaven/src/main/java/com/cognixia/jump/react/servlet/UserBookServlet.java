package com.cognixia.jump.react.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cognixia.jump.react.book.Book;
import com.cognixia.jump.react.book.BookDAOClass;
import com.cognixia.jump.react.userbook.UserBook;
import com.cognixia.jump.react.userbook.UserBooksDAOClass;
import com.google.gson.Gson;

@WebServlet("/UserBookServlet")
public class UserBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserBooksDAOClass userBooks = new UserBooksDAOClass();
	private BookDAOClass BookDAO = new BookDAOClass();
	private Gson gson = new Gson();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String s = new BufferedReader( 
				new InputStreamReader(request.getInputStream()))
				.lines()
				.collect(Collectors.joining("\n"));
		
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
		// parsing the string into json object
		json = (JSONObject) parser.parse(s);
		// json to Object
		Object id = json.get("userID");
		// object to Long
		Long lid = (Long) id;
		// Long to int
		int uid = lid.intValue();
		
		List<UserBook> userBookList = userBooks.getUserBookbyId(uid);
		String jsonobj  = gson.toJson(userBookList);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonobj);
		out.flush();
		
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if(br != null){
			json = br.readLine();
		}

		JSONParser parser = new JSONParser();
		
		try {
			JSONObject injson = (JSONObject) parser.parse(json);
			
			String username = (String) injson.get("bookID");
			int bookID = Integer.parseInt(username);
			String userID1 = (String) injson.get("userID");
			int userID = Integer.parseInt(userID1); 
			String currentpage = (String) injson.get("currentPage");
			int currentPage = Integer.parseInt(currentpage); 
			String currstatus = (String) injson.get("currStatus");
			char currStatus = currstatus.charAt(0);
			String rating = (String) injson.get("rating");
			int Rating = Integer.parseInt(rating); 
			Book book = BookDAO.getBookbyId(bookID);
			
			UserBook newBook = new UserBook(book.getBookId(), book.getTitle(), book.getAuthor(),
					book.getPublisher(),book.getPageCount() , book.getGenre(), book.getSeriesId(),book.getSeriesOrder() , book.isReleased(), book.getFranchiseId(),
					book.getCoverURL(), book.getDescription(),userID, currentPage, currStatus, Rating);
			userBooks.updateUserBook(newBook);
		
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}

}
