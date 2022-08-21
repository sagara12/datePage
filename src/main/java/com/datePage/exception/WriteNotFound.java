package com.datePage.exception;


// status :404
public class WriteNotFound extends DatePageException{

    private static final String MESSAGE  = "존재하지 않는 글입니다.";

    public WriteNotFound() {
        super(MESSAGE);
    }

    /*public WriteNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }*/

    @Override
    public int statusCode() {
        return 404;
    }
}
