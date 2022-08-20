package com.datePage.exception;

public class WriteNotFound extends RuntimeException{

    private static final String MESSAGE  = "존재하지 않는 글입니다.";

    public WriteNotFound() {
        super(MESSAGE);
    }

    /*public WriteNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }*/
}
