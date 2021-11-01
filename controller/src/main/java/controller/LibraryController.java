package controller;

import models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class LibraryController {
    private final Book[] cellsWithBooks;

    public LibraryController(@NotNull Library library, int libraryCapacity) {
        this.cellsWithBooks = new Book[libraryCapacity];
        fillCellsWithBooks((ArrayList<Book>)library.getCollectionBooks());
    }

    private void fillCellsWithBooks(@NotNull ArrayList<Book> listBooks) {
        if (listBooks.size() <= cellsWithBooks.length) {
            for (int i = 0; i < listBooks.size(); i++) {
                cellsWithBooks[i] = listBooks.get(i);
            }
        } else {
            throw new IndexOutOfBoundsException("Not all books can fit on the shelves");
        }
    }

    public void addBook(Book book) throws Exception {
        boolean addedBook = false;
        for (int i = 0; i < cellsWithBooks.length; i++) {
            if (cellsWithBooks[i] == null) {
                cellsWithBooks[i] = book;
                addedBook = true;
                break;
            }
        }
        if (!addedBook) {
            throw new Exception("All cells are occupied");
        }
    }

    @NotNull
    public Optional<Book> takeBook(int index) {
        if (cellsWithBooks[index] == null) {
            throw new NullPointerException(String.format("There is no book in cell %d", index));
        }
        Optional<Book> opt;
        try {
            System.out.printf("From cell %d was taken: %s.\n", index, cellsWithBooks[index]);
            opt = Optional.ofNullable(cellsWithBooks[index]);
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving information from the book.\n");
            opt = Optional.empty();
        } finally {
            cellsWithBooks[index] = null;
        }
        return opt;
    }

    public Optional<Book[]> getCellsWithBooks() {
        return Optional.ofNullable(cellsWithBooks);
    }

    public void printContentsOfCellsToConsole() {
        int count = Math.toIntExact(Arrays.stream(cellsWithBooks).filter(Objects::nonNull).count());
        System.out.printf("Cells occupied %d of %d.\n", count, cellsWithBooks.length);
        /*for (int i = 0; i < cellsWithBooks.length; i++) {
            System.out.println(cellsWithBooks[i]);
        }*/
    }
}
