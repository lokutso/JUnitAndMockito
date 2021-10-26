package controller;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

public interface BooksFactory {
    Collection<Book> books();
}
