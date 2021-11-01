package models;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Author {
    @NotNull
    private String name;

    public Author(@NotNull String name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
