package com.cognixia.jump.react.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cognixia.jump.react.customExceptions.UsernameAlreadyTakenException;
import com.cognixia.jump.react.user.User;
import com.cognixia.jump.react.user.UserDAOClass;



@WebServlet("/UserCreationServlet")
public class UserCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if(br != null){
			json = br.readLine();
		}

		JSONParser parser = new JSONParser();
		
		try {
			JSONObject injson = (JSONObject) parser.parse(json);
			
			String username = (String) injson.get("username");
			String password = (String) injson.get("password");
			
			System.out.println(username+" "+password);
		
		User user = new User(0, username, password);
		
		boolean login =false;
		UserDAOClass userDAOClass = new UserDAOClass();
			userDAOClass.addUser(user);
			try{ 
				login = userDAOClass.authenticateUser(username, password);
				
				if(login == false) {
					user = userDAOClass.getUserbyUsername(username);
					if(user == null) {
						userDAOClass.addUser(user);
					}else {
						throw new UsernameAlreadyTakenException();
					}
					
				}
				
			}catch(UsernameAlreadyTakenException e){
				e.getMessage();
				
			}
	
	}catch(ParseException e) {
		e.printStackTrace();
	}
}
}
