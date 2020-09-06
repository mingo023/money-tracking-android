package com.cs50.finalprojectcs50.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.Category;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.utils.DateConverters;
import com.cs50.finalprojectcs50.utils.FieldValidation;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionForm extends AppCompatActivity {
    protected AutoCompleteTextView categoryInput;
    protected TextInputEditText datePickerInput;
    protected TextInputEditText amountInput;
    protected TextInputEditText noteInput;

    protected TextInputLayout amountLayout;
    protected TextInputLayout noteLayout;
    protected TextInputLayout categoryLayout;
    protected TextInputLayout dateLayout;
    protected MaterialButton submitTransactionBtn;

    protected void initializeViews() {
        categoryInput = findViewById(R.id.category_dropdown);
        datePickerInput = findViewById(R.id.date_picker);
        amountInput = findViewById(R.id.amount_input);
        noteInput = findViewById(R.id.note_input);

        amountLayout = findViewById(R.id.amount_input_layout);
        noteLayout = findViewById(R.id.note_input_layout);
        categoryLayout = findViewById(R.id.category_input_layout);
        dateLayout = findViewById(R.id.date_picker_layout);

        submitTransactionBtn = findViewById(R.id.submit_transaction_btn);
    }

    protected void initializeCategorySelector() {
        List<String> options = new ArrayList<>();

        List<Category> categories = AppDatabase.getInstance(getApplicationContext()).categoryDao().getCategories();
        for (Category category : categories) {
            options.add(category.name);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_category,
                        options);

        categoryInput.setAdapter(adapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void handleDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        final MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Long>) selection -> {
            Date date = new Date(selection);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            datePickerInput.setText(format.format(date));
        });

        datePickerInput.setOnTouchListener((v, event) -> {
            String fragmentTag = "date_picker";
            FragmentManager fm = getSupportFragmentManager();
            Fragment oldFragment = fm.findFragmentByTag(fragmentTag);

            if (oldFragment != null) return false;
            materialDatePicker.show(getSupportFragmentManager(), fragmentTag);
            return true;
        });
    }

    protected boolean isValidForm(String amount, String note, String category, String date) {
        int errorCount = 0;

        FieldValidation amountValidation = new FieldValidation(amountLayout);
        errorCount += amountValidation.required(amount, "Amount field is required").validate();

        FieldValidation noteValidation = new FieldValidation(noteLayout);
        errorCount += noteValidation.required(note, "Note field is required").validate();

        FieldValidation categoryValidation = new FieldValidation(categoryLayout);
        errorCount += categoryValidation.required(category, "Category field is required").validate();

        FieldValidation dateValidation = new FieldValidation(dateLayout);
        errorCount += dateValidation.required(date, "Date field is required").validate();

        return errorCount == 0;
    }

    protected Transaction submitForm() {
        long amount = Integer.parseInt(amountInput.getText().toString());
        String note = noteInput.getText().toString();
        String category = categoryInput.getText().toString();
        String date = datePickerInput.getText().toString();

        if (!isValidForm(amountInput.getText().toString(), note, category, date)) {
            return null;
        }

        String categoryId = AppDatabase.getInstance(this).categoryDao().findCategoryByName(category).id;

        return new Transaction(
                amount,
                note,
                DateConverters.transformDateFormat(date, "dd/MM/yyyy"),
                categoryId
        );
    }
}
