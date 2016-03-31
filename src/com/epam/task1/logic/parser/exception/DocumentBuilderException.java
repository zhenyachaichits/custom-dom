package com.epam.task1.logic.parser.exception;

/**
 * Created by Zheny Chaichits on 12/1/2015.
 */
public class DocumentBuilderException extends Exception {

    public DocumentBuilderException() {
        super();
    }

    public DocumentBuilderException(String message) {
        super(message);
    }

    public DocumentBuilderException(String message, Exception cause) {
        super(message, cause);
    }
}
