package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.cs50.finalprojectcs50.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<Category> getCategories();

    @Query("INSERT INTO categories (name) VALUES ('Food')")
    void create();
}
