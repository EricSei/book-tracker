package com.cognixia.jump.react.customExceptions;

public class IncorrectPasswordException extends Exception {

	public IncorrectPasswordException() {
		// TODO Auto-generated constructor stub
		System.out.println("Wrong password");
	}

	public IncorrectPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



}
