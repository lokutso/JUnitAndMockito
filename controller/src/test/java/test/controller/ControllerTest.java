package test.controller;

import com.google.inject.*;
import controller.BooksFactory;
import controller.FileBookFactory;
import controller.LibraryFactory;
import models.Author;
import models.Book;
import net.lamberto.junit.GuiceJUnitRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules(ControllerTest.TestModule.class)
public class ControllerTest {

    public static class TestModule extends AbstractModule {
        @NotNull
        private static final String fileName = "src/main/resources/books.txt";

        @Override
        protected void configure() {
            bind(BooksFactory.class).toProvider(File.class);
        }

        public static final class File implements Provider<BooksFactory> {
            @NotNull
            @Override
            public BooksFactory get() {
                return new FileBookFactory(fileName);
            }
        }
    }

    @Inject
    public BooksFactory booksFactory;
    @NotNull
    private LibraryFactory libraryFactory;
    int libraryCapacity = 100;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new TestModule());
        libraryFactory = injector.getInstance(LibraryFactory.class);
    }

    @Test
    public void whenUseMock() {
        FileBookFactory mockFileBookFactory = Mockito.mock(FileBookFactory.class);
        Mockito.when(mockFileBookFactory.books()).thenReturn(new ArrayList<>());
    }

    @Test(expected = NullPointerException.class)
    public void whenUseSpy() {
        Injector injector = Guice.createInjector(new TestModule());
        libraryFactory = Mockito.spy(injector.getInstance(LibraryFactory.class));
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        Answer newBook = invocation -> new Book(new Author(Mockito.anyString()), Mockito.anyString());
        Mockito.doAnswer(newBook)
                .when(libraryFactory)
                .getLibraryController()
                .takeBook(Mockito.anyByte());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkLibraryCapacityIsLessThanBooks() {
        libraryFactory.loadLibrary(booksFactory, 90);
    }

    @Test
    public void checkNumberOfBooksInCells() {
        /*We assume that there are 100 books loaded into the library.
        Then there should be 100 books in the cells. */
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        long numberOfBooksInLibrary = 100;
        long actual = 0;
        Optional<Book[]> cellsWithBooks = libraryFactory.getLibraryController().getCellsWithBooks();
        if (cellsWithBooks.isPresent()) {
            actual = Arrays.stream(cellsWithBooks.get()).filter(Objects::nonNull).count();
        }
        Assert.assertEquals(numberOfBooksInLibrary, actual);
    }

    @Test(expected = NullPointerException.class)
    public void checkForTakeBookFromEmptyCell() {
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        libraryFactory.getLibraryController().takeBook(99);
        libraryFactory.getLibraryController().takeBook(99);
    }

    @Test
    public void checkReturnBooksForCompliance() {
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        Book book = libraryFactory.getLibraryController().getCellsWithBooks().get()[99];
        Book actual = libraryFactory.getLibraryController().takeBook(99).get();
        Assert.assertEquals(book, actual);
    }

    @Test
    public void checkAddedBookForCompliance() throws Exception {
        libraryFactory.loadLibrary(booksFactory, libraryCapacity + 1);
        Book book = new Book(new Author("Author10"), "Book 10");
        libraryFactory.getLibraryController().addBook(book);
        Book actual = libraryFactory.getLibraryController().takeBook(libraryCapacity).get();
        Assert.assertEquals(book, actual);
    }

    @Test(expected = Exception.class)
    public void checkForAddWhenAllCellsAreFull() throws Exception {
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        Book book = new Book(new Author("Author10"), "Book 10");
        libraryFactory.getLibraryController().addBook(book);
    }

    @Test
    public void checkPrintContentToConsole() {
        libraryFactory.loadLibrary(booksFactory, libraryCapacity);
        libraryFactory.getLibraryController().printContentsOfCellsToConsole();
    }
}
