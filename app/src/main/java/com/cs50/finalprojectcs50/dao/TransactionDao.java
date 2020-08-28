package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.cs50.finalprojectcs50.model.Transaction;

import java.util.Date;
import java.util.List;

@Dao
public interface TransactionDao {
    @Query("INSERT INTO transactions (amount, date, categoryId) VALUES (:amount, :date, :categoryId)")
    void create(long amount, Date date, int categoryId);

    @Query("SELECT * FROM transactions WHERE date BETWEEN :start AND :end")
    List<Transaction> getTransactions(Date start, Date end);

    @Query("SELECT * FROM transactions WHERE categoryId = :categoryId")
    List<Transaction> getTransactionsByCategory(int categoryId);
}
