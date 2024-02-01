package com.ojiraphers.section.problem;

public class Category {
    private int catrgoryCode;
    private String categoryName;

    public Category() {
    }

    public Category(int catrgoryCode, String categoryName) {
        this.catrgoryCode = catrgoryCode;
        this.categoryName = categoryName;
    }

    public int getCatrgoryCode() {
        return catrgoryCode;
    }

    public void setCatrgoryCode(int catrgoryCode) {
        this.catrgoryCode = catrgoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
    }
}
