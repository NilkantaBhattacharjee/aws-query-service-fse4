package com.cts.skilltracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CriteriaNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public CriteriaNotFoundException(String message) {
        super(message);
    }

}
