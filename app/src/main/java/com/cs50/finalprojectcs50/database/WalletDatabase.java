package com.cs50.finalprojectcs50.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cs50.finalprojectcs50.dao.WalletDao;
import com.cs50.finalprojectcs50.model.Wallet;

@Database(entities = {Wallet.class}, version = 1, exportSchema = false)
public abstract class WalletDatabase extends RoomDatabase {
    public abstract WalletDao walletDao();
}
