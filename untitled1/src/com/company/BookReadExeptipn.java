package com.company;

public class BookReadExeptipn extends Exception
{
    public BookReadExeptipn() {
    }

    public BookReadExeptipn(String message) {
        super(message);
    }

    public BookReadExeptipn(String message, Throwable cause) {
        super(message, cause);
    }

    public BookReadExeptipn(Throwable cause) {
        super(cause);
    }

    public BookReadExeptipn(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
