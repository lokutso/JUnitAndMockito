package controller;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class Library {
    @NotNull
    private final Collection<Book> collectionBooks;

    public Library(@NotNull Collection<Book> listBooks) {
        this.collectionBooks = listBooks;
    }

    public @NotNull Collection<Book> getCollectionBooks() {
        return collectionBooks;
    }

    public void printContentsToConsole() {
        for (Book book : collectionBooks) {
            System.out.println(book);
        }
    }
}
