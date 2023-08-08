package org.gigsoft.secondoblog.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e) {
		return "<h1>%s</h1>".formatted(e.getMessage());		
	}
}
