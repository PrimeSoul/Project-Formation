package com.ats.ProjectAtS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends Exception {

	private static final long serialVersionUID = 6850323151059452129L;
	
    public NotFound() {
        super();
    }

    public NotFound(String error) {
        super(error);
    }

}
