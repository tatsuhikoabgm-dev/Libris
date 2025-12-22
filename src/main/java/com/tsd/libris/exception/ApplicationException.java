package com.tsd.libris.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

	public enum Type{
		INVALID_REQUEST,
		SYSTEM
	}
	
	private final Type type;
	
	public ApplicationException(Type type,String message) {
		super(message);
		this.type = type;
	}
	
}
