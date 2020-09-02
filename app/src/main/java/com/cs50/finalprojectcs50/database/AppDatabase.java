package com.cs50.finalprojectcs50.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cs50.finalprojectcs50.dao.CategoryDao;
import com.cs50.finalprojectcs50.dao.TransactionDao;
import com.cs50.finalprojectcs50.model.Category;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.utils.DateConverters;

@Database(entities = {Transaction.class, Category.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract TransactionDao transactionDao();
    public abstract CategoryDao categoryDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }
}
