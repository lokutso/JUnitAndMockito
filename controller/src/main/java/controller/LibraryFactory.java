package controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

public class LibraryFactory {
    @NotNull
    private final BooksFactory fileBookFactory;
    @Nullable
    private LibraryController libraryController;

    @Inject
    public LibraryFactory(@NotNull FileBookFactory fileBookFactory) {
        this.fileBookFactory = fileBookFactory;
    }

    public void loadLibrary(int libraryCapacity) {
        libraryController = new LibraryController(new Library(fileBookFactory.books()), libraryCapacity);
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
