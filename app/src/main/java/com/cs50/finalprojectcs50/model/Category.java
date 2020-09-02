package com.cs50.finalprojectcs50.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    public Category(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
