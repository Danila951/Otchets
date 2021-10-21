package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Book b = new Book("fgdg", "1234", 25);

        List<Book> b1 = new ArrayList<>(Arrays.asList(
                null,
                null,
                b
        ));


        Library library = new Library("fregt",b1);

        library.addBook("erfgdg", "1234", 25);
        System.out.println(library);
        library.removeBook("fgdg", "1234", 25);
        System.out.println(library);
    }
}
