package com.cs50.finalprojectcs50.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cs50.finalprojectcs50.dao.CategoryDao;
import com.cs50.finalprojectcs50.model.Category;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
}
