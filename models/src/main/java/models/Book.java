package models;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Book {
    @NotNull
    private Author author;
    @NotNull
    private String name;

    public Book(@NotNull Author author, @NotNull String name) {
        this.author = author;
        this.name = name;
    }

    @NotNull
    public Optional<Author> getAuthor() {
        return Optional.ofNullable(author);
    }

    @NotNull
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setAuthor(@NotNull Author author) {
        this.author = author;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", name='" + name + '\'' +
                '}';
    }
}
