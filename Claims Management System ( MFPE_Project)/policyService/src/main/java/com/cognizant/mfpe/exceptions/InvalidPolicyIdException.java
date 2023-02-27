package com.cognizant.mfpe.exceptions;

public class InvalidPolicyIdException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidPolicyIdException(String msg) {
		super(msg);
	}

}
