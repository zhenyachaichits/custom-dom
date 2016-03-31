package com.epam.task1.logic.parser.exception;

/**
 * Created by Zheny Chaichits on 12/3/2015.
 */
public class DocumentRootNotFoundException extends Exception {

    public DocumentRootNotFoundException() {
        super();
    }

    public DocumentRootNotFoundException(String message) {
        super(message);
    }

    public DocumentRootNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}