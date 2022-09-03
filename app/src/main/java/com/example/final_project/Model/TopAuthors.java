package com.example.final_project.Model;

public class TopAuthors {
    private int image;
    private String authorName;
    private String bookName;

    public TopAuthors(int image, String authorName, String bookName) {
        this.image = image;
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public int getImage() {
        return image;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookName() {
        return bookName;
    }
}
