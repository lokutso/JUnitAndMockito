package models;

public class Book {
    private Author author;
    private String name;

    public Book(Author author, String name) {
        this.author = author;
        this.name = name;
    }

    public Author getAuthor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }
}
