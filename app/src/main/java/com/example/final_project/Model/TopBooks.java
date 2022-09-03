package com.example.final_project.Model;

public class TopBooks {
    private int image;
    private String authorName;
    private String bookName;

    public void setImage(int image) {
        this.image = image;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) {
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
