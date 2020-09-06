package com.cs50.finalprojectcs50.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.Transaction;

public class UpdateTransactionActivity extends TransactionForm {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_transaction);
        super.initializeViews();

        Intent intent = getIntent();
        String transactionId = intent.getStringExtra("id");
        amountInput.setText(String.valueOf(intent.getLongExtra("amount", 0)));
        noteInput.setText(intent.getStringExtra("note"));
        categoryInput.setText(intent.getStringExtra("category"));
        datePickerInput.setText(intent.getStringExtra("date"));

        initializeCategorySelector();
        handleDatePicker();

        submitTransactionBtn.setOnClickListener(v -> {
            updateTransaction(transactionId);
        });
    }

    private void updateTransaction(String id) {
        Transaction transaction = submitForm();
        if (transaction == null) {
            return;
        }
        AppDatabase.getInstance(this)
                .transactionDao()
                .update(
                        id,
                        transaction.amount,
                        transaction.note,
                        transaction.categoryId,
                        transaction.date.getTime()
                );

        finish();
    }
}
