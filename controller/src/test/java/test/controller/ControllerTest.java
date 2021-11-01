package test.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.BooksFactory;
import controller.LibraryFactory;
import controller.LibraryModule;
import org.junit.Test;

import java.io.IOException;

public class ControllerTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkLibraryCapacityIsLessThanBooks() throws IOException {
        Injector injector = Guice.createInjector(new LibraryModule(new String[]{}));
        LibraryFactory libraryFactory = injector.getInstance(LibraryFactory.class);
        libraryFactory.loadLibrary(injector.getInstance(BooksFactory.class), 90);
    }
}
