package controller;

import com.google.inject.AbstractModule;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BooksFactory.class).to(FileBookFactory.class);
    }
}
