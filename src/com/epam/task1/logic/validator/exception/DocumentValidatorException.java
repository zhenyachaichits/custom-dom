package com.epam.task1.logic.validator.exception;

/**
 * Created by Zheny Chaichits on 11/29/2015.
 */
public class DocumentValidatorException extends Exception {

    public DocumentValidatorException() {
        super();
    }

    public DocumentValidatorException(String message) {
        super(message);
    }

    public DocumentValidatorException(String message, Exception cause) {
        super(message, cause);
    }
}
