package com.akamaxping;

import java.util.ArrayList;
import java.util.List;

public class TempDb {
    public static List<Author> getAuthorsFromTempDb() {
        return new ArrayList<Author>() {
            {
                add(Author.newBuilder().setAuthorId(1).setBookId(1).setFirstName("Charles").setLastName("Dickens").setGender("male").build());
                add(Author.newBuilder().setAuthorId(2).setFirstName("William").setLastName("Shakespeare").setGender("male").build());
                add(Author.newBuilder().setAuthorId(3).setFirstName("JK").setLastName("Rolling").setGender("female").build());
                add(Author.newBuilder().setAuthorId(4).setFirstName("Virginia").setLastName("Woolf").setGender("male").build());
            }
        };
    }

    public static List<Book> getBooksFromTempDb() {
        return new ArrayList<Book>() {
            {
                add(Book.newBuilder().setBookId(1).setAuthorId(1).setTitle("Oliver Twist").setPrice(123.3f).setPages(100).build());
                add(Book.newBuilder().setBookId(2).setAuthorId(1).setTitle("A Christmas Carol").setPrice(1123.3f).setPages(150).build());
                add(Book.newBuilder().setBookId(3).setAuthorId(2).setTitle("Hamlet").setPrice(732.3f).setPages(250).build());
                add(Book.newBuilder().setBookId(4).setAuthorId(3).setTitle("Harry Potter").setPrice(432.21f).setPages(543).build());
                add(Book.newBuilder().setBookId(5).setAuthorId(3).setTitle("The Casual Vacancy").setPrice(453.4f).setPages(654).build());
                add(Book.newBuilder().setBookId(6).setAuthorId(4).setTitle("Mre. Donal").setPrice(765.3f).setPages(123).build());
            }
        };
    }
}
