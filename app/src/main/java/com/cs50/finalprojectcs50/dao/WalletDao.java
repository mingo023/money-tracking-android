package com.cs50.finalprojectcs50.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.cs50.finalprojectcs50.model.Wallet;

import java.util.List;

@Dao
public interface WalletDao {
    @Query("INSERT INTO wallets (balance) VALUES (:balance)")
    void create(long balance);

    @Query("SELECT * FROM wallets")
    List<Wallet> getWallets();
}
