package com.cs50.finalprojectcs50.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class TransactionAndCategory {
    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "category_id",
            entityColumn = "id"
    )
    public Category category;

    @Override
    public String toString() {
        return "TransactionAndCategory{" +
                "transaction=" + transaction +
                ", category=" + category +
                '}';
    }
}
