package com.app.expensetracker.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cat_id",nullable = false)
    private String cat_id;
    @Column(name="categoryName")
    private String categoryName;
    private List<String> users;
    public Category() {
    }

    public Category(String cat_id, String categoryName, List<String> users) {
        this.cat_id = cat_id;
        this.categoryName = categoryName;
        this.users = users;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cat_id='" + cat_id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", users=" + users +
                '}';
    }
}
