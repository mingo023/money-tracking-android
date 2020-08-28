package com.cs50.finalprojectcs50.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public long amount;

    @ColumnInfo
    public Date date;

    @ForeignKey(entity = Category.class,
            parentColumns = "id",
            childColumns = "categoryId"
    )
    public int categoryId;
}
