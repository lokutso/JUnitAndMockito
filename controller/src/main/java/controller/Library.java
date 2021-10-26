package controller;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class Library {
    @NotNull
    private ArrayList<Book> listBooks;

    public Library(@NotNull Collection<Book> listBooks) {
        this.listBooks = (ArrayList<Book>)listBooks;
    }

    public @NotNull ArrayList<Book> getListBooks() {
        return this.listBooks;
    }

    public void printContentsToConsole() {
        for (Book book : listBooks) {
            System.out.printf("Book: %s, Author: %s", book.getName(), book.getAuthor().getName());
        }
    }
}
