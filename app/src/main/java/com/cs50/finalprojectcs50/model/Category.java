package com.cs50.finalprojectcs50.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;
}
