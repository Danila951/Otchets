package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Library {
    private String address;
    private Book[] books;

    public Library(String address, Book[] books) {
        this.address = address;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "address='" + address + '\'' +
                ", books=" + books +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public boolean addBook(Book b) {
        var idx = Arrays.asList(this.books).indexOf(null);

        if (idx != -1) {
            this.books[idx] = b;
            return true;
        } else {
            return false;
        }
    }

    public void ReadBook() throws BookReadExeptipn, LibraryReadExepion  {
        Scanner asd = new Scanner(System.in);
        String title;
        System.out.printf("Введите title от 3 до 20 символов: ");
        title = asd.nextLine();

        String author;
        System.out.printf("Введите author от 3 до 20 символов: ");
        author = asd.nextLine();

        int page;
        System.out.printf("Введите page: ");
        try {
            page = Integer.parseInt(asd.nextLine());
        } catch (Exception e) {
            System.out.println("Э, слыш, кличество страниц, это число, а ты, это ошибка природы, раз этого не знал");
            throw new BookReadExeptipn(e);
        }

        if(title.length()<3 || title.length()>20) {
            System.out.println("Название не корректно");
            throw new BookReadExeptipn();
        } else if (author.length()<3 || author.length()>20) {
            System.out.println("Автор не корректен");
            throw new BookReadExeptipn();
        } else if (page<0) {
            System.out.println("Количество страниц не коректно");
            throw new BookReadExeptipn();
        } else {
            System.out.println("Все ведено корректно");
        }

        Book book = new Book(title,author,page);
        if(!addBook(book)){
            throw new LibraryReadExepion(book);
        } else {System.out.println("Книга доавлена");}

    }
}
