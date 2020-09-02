package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cs50.finalprojectcs50.model.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("INSERT INTO transactions (amount, note, date, category_id) VALUES (:amount, :note, :date, :categoryId)")
    void create(long amount, String note, String date, String categoryId);

    @Query("SELECT * FROM transactions WHERE date BETWEEN :start AND :end")
    List<Transaction> getTransactions(String start, String end);

    @Query("SELECT * FROM transactions")
    List<Transaction> getTransactions();

    @Query("SELECT * FROM transactions WHERE category_id = :categoryId")
    List<Transaction> getTransactionsByCategory(int categoryId);


    @Insert
    void insert(Transaction transaction);
}
