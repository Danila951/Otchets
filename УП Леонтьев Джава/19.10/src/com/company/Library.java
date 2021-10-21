package com.company;

import java.util.List;
import java.util.Objects;

public class Library {

    @Override
    public String toString() {
        return "Library{" +
                "address='" + address + '\'' +
                ", books=" + books +
                '}';
    }

    private String address;
    private List<Book> books;

    public Library(String address, List<Book> books) {
        this.address = address;
        this.books = books;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean hasBook(String author, String title, int pages) {
        for (Book book : books) {
            if (book!=null&&book.author.equals(author) && book.title.equals(title) && book.pages == pages) {
                return true;
            }
        }
        return false;
    }

    public Book addBook(String author, String title, int pages) {
        Book b = new Book(title,author,pages);
        if(!this.hasBook(title, author, pages)) {
            books.add(b);
            return b;
        } else {
            return null;
        }
    }

    public Book removeBook(String author, String title, int pages) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null && Objects.equals(books.get(i).getAuthor(), author) && Objects.equals(books.get(i).getTitle(), title) && books.get(i).getPages() == pages) {
                Book b = books.get(i);
                books.remove(i);
                return b;
            }
        }
        return null;
    }
}
