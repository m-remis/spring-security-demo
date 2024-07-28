package com.michal.examples.spring.security.exception;

public class CardNotFoundException extends RuntimeException {

    private final String msg;

    public CardNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public static CardNotFoundException of(String msg) {
        return new CardNotFoundException(msg);
    }

    public String getMsg() {
        return msg;
    }
}
