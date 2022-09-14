package com.onlinebookplatformselection.models.pagedetails;

public class PageDetails {

    public String bookName;
    public String publisher;
    public String author;
    public int qty;
    public String isbnNumber;

    public PageDetails() {
    }

    private String availability;
    private String price;

    public String getBookName() {
        return bookName;
    }

    public PageDetails setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public PageDetails setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PageDetails setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getQty() {
        return qty;
    }

    public PageDetails setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public PageDetails setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
        return this;
    }

    public String getAvailability() {
        return availability;
    }

    public PageDetails setAvailability(String availability) {
        this.availability = availability;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public PageDetails setPrice(String price) {
        this.price = price;
        return this;
    }
}
