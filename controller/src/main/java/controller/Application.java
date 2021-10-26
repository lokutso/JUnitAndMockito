package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

public class Application {
    @NotNull
    private final LibraryFactory libraryFactory;

    @Inject
    public Application(@NotNull LibraryFactory libraryFactory) {
        this.libraryFactory = libraryFactory;
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new LibraryModule());
        injector.getInstance(Application.class).run();
    }

    public void run() {

    }
}
