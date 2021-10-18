package com.company;

public class LibraryReadExepion extends Exception{

    Book book;

    public LibraryReadExepion(Book book) {
        this.book = book;
    }

    public LibraryReadExepion(String message, Book book) {
        super(message);
        this.book = book;
    }

    public LibraryReadExepion(String message, Throwable cause, Book book) {
        super(message, cause);
        this.book = book;
    }

    public LibraryReadExepion(Throwable cause, Book book) {
        super(cause);
        this.book = book;
    }

    public LibraryReadExepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Book book) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.book = book;
    }
}
