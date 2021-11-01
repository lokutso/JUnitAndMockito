package controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

public class LibraryFactory {
    @Nullable
    private LibraryController libraryController;

    public void loadLibrary(@NotNull BooksFactory booksFactory, int libraryCapacity) {
        libraryController = new LibraryController(new Library(booksFactory.books()), libraryCapacity);
    }

    @NotNull
    public LibraryController getLibraryController() {
        if (libraryController != null) {
            return libraryController;
        } else {
            throw new IllegalStateException();
        }
    }
}
