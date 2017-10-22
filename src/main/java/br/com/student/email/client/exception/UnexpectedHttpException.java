package br.com.student.email.client.exception;

import org.springframework.http.HttpStatus;

public class UnexpectedHttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;
	
	public UnexpectedHttpException(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	public HttpStatus getStatusCode() {
		return this.statusCode;
	}
}
