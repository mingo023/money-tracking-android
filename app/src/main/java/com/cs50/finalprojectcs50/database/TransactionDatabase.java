package com.cs50.finalprojectcs50.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cs50.finalprojectcs50.dao.TransactionDao;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.utils.Converters;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
