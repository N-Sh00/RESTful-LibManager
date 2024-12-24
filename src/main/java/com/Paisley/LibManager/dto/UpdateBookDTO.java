package com.Paisley.LibManager.dto;

public class UpdateBookDTO {
    private String name;
    private Boolean isAvailable;
    private Long borrowedById;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Long getBorrowedById() {
        return borrowedById;
    }

    public void setBorrowedById(Long borrowedById) {
        this.borrowedById = borrowedById;
    }
}
