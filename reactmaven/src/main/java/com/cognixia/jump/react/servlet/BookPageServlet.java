package com.cognixia.jump.react.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.react.book.Book;
import com.cognixia.jump.react.book.BookDAOClass;
import com.google.gson.Gson;


@WebServlet("/BookServlet")
public class BookPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BookDAOClass bookDAO = new BookDAOClass();
		List<Book> BookList = bookDAO.getAllBooks();
		String jsonobj  = gson.toJson(BookList);
	
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonobj);
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
