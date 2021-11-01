package controller;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.jetbrains.annotations.NotNull;

public class LibraryModule extends AbstractModule {
    @NotNull
    private static String fileName;

    @Inject
    public LibraryModule(@NotNull String[] args) {
        try {
            fileName = args[0];
        } catch (Exception e) {
            fileName = "src/main/resources/books.txt";
        }
    }

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
