package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cs50.finalprojectcs50.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<Category> getCategories();

    @Query("SELECT * FROM categories WHERE name=:name")
    Category findCategoryByName(String name);

    @Insert
    void insert(Category category);
}
