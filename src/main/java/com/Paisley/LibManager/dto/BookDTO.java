package com.Paisley.LibManager.dto;

public class BookDTO {
    private Long id;
    private String name;
    private boolean isAvailable;
    private Long borrowedById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Long getBorrowedById() {
        return borrowedById;
    }

    public void setBorrowedById(Long borrowedById) {
        this.borrowedById = borrowedById;
    }
}


