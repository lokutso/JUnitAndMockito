package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import models.Author;
import models.Book;
import org.jetbrains.annotations.NotNull;

public class Application {
    @NotNull
    private final BooksFactory bookFactory;

    @Inject
    public Application(@NotNull BooksFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new LibraryModule(args));
        injector.getInstance(Application.class).run(args);
    }

    public void run(@NotNull String[] args) {
        int libraryCapacity = 0;
        try {
            libraryCapacity = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Invalid argument: library size");
            libraryCapacity = 100;
        }

        LibraryFactory libraryFactory = new LibraryFactory();
        libraryFactory.loadLibrary(bookFactory, libraryCapacity);
        libraryFactory.getLibraryController().takeBook(1);
        libraryFactory.getLibraryController().printContentsOfCellsToConsole();
        try {
            libraryFactory.getLibraryController().addBook(new Book(new Author("Author1"), "Book1"));
        } catch (Exception e) {
            e.getMessage();
        }
        libraryFactory.getLibraryController().printContentsOfCellsToConsole();
    }
}
