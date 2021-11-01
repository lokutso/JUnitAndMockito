package controller;

import models.Book;

import java.util.Collection;

public interface BooksFactory {
    Collection<Book> books();
}
