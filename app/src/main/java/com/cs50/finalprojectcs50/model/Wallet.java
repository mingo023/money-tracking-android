package com.cs50.finalprojectcs50.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wallets")
public class Wallet {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "balance")
    public long balance;
}
