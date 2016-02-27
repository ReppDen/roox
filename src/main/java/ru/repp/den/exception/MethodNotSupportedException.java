package ru.repp.den.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class MethodNotSupportedException extends RuntimeException {

    public MethodNotSupportedException(String message) {
        super(message);
    }
}
