package com.example.quizzy.dto;

public class CategoryDto {

    private String category;

    public CategoryDto(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}