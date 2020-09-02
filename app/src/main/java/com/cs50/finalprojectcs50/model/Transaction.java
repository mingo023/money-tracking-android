package com.cs50.finalprojectcs50.model;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "transactions",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = CASCADE
        )
)
public class Transaction {
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo
    public long amount;

    @ColumnInfo
    public String note;

    @ColumnInfo
    public Date date;

    @ColumnInfo(name = "category_id")
    public String categoryId;

    public Transaction(long amount, String note, Date date, String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
