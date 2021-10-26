package controller;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LibraryController {
    @NotNull
    private final Book[] cellsWithBooks;

    public LibraryController(@NotNull Library library, int libraryCapacity) {
        this.cellsWithBooks = new Book[libraryCapacity];

        if (library.getListBooks().size() <= libraryCapacity) {
            for (int i = 0; i < library.getListBooks().size(); i++) {
                cellsWithBooks[i] = library.getListBooks().get(i);
            }
        } else {
            throw new IndexOutOfBoundsException("")
        }
    }
}
