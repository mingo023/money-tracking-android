package com.cs50.finalprojectcs50.activity;

import android.os.Bundle;
import android.view.View;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.utils.DateConverters;

import java.util.Date;

public class CreateTransactionActivity extends TransactionForm {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_transaction_activity);
        super.initializeViews();

        categoryInput = findViewById(R.id.category_dropdown);
        datePickerInput = findViewById(R.id.date_picker);
        amountInput = findViewById(R.id.amount_input);
        noteInput = findViewById(R.id.note_input);

        amountLayout = findViewById(R.id.amount_input_layout);
        noteLayout = findViewById(R.id.note_input_layout);
        categoryLayout = findViewById(R.id.category_input_layout);
        dateLayout = findViewById(R.id.date_picker_layout);

        datePickerInput.setText(DateConverters.formatDate(new Date(), "dd/MM/yyyy"));

        submitTransactionBtn.setOnClickListener(v -> addTransaction(v));

        initializeCategorySelector();
        handleDatePicker();
    }

    public void addTransaction(View v) {
        Transaction transaction = submitForm();
        if (transaction == null) {
            return;
        }

        AppDatabase.getInstance(this).transactionDao().insert(transaction);

        finish();
    }
}