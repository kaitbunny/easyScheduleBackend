package com.easySchedule.backend.domain.exception;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String msg) {
		super(msg);
	}
	
	public NegocioException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
