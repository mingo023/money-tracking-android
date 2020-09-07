package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cs50.finalprojectcs50.model.SumAmountTransaction;
import com.cs50.finalprojectcs50.model.SumTransactionWithCategory;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.model.TransactionAndCategory;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("SELECT date, SUM(amount) as amount FROM transactions WHERE date >=:start AND date <=:end GROUP BY date")
    List<SumAmountTransaction> getGroupedTransactions(long start, long end);

    @Query("SELECT SUM(amount) as amount, categories.name as categoryName " +
            "FROM transactions INNER JOIN categories ON transactions.category_id=categories.id " +
            "WHERE date >=:start AND date <=:end " +
            "GROUP BY categories.id")
    List<SumTransactionWithCategory> getSumAmountWithCategory(long start, long end);

    @androidx.room.Transaction
    @Query("SELECT * FROM transactions")
    List<TransactionAndCategory> getTransactionsAndCategory();

    @androidx.room.Transaction
    @Query("SELECT * FROM transactions WHERE date >= :start AND date <= :end")
    List<TransactionAndCategory> getTransactionsAndCategory(long start, long end);

    @Query("DELETE FROM transactions WHERE id=:id")
    void delete(String id);

    @Query("UPDATE transactions SET amount=:amount, note =:note, category_id=:categoryId, date=:date WHERE id=:id")
    void update(String id, long amount, String note, String categoryId, long date);

}
