package com.cs50.finalprojectcs50.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.Category;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.utils.DateConverters;
import com.cs50.finalprojectcs50.utils.FieldValidation;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityCreateTransaction extends AppCompatActivity {
    private AutoCompleteTextView dropDownCategory;
    private TextInputEditText datePickerInput;
    private TextInputEditText amountInput;
    private TextInputEditText noteInput;

    private TextInputLayout amountLayout;
    private TextInputLayout noteLayout;
    private TextInputLayout categoryLayout;
    private TextInputLayout dateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        dropDownCategory = findViewById(R.id.category_dropdown);
        datePickerInput = findViewById(R.id.date_picker);
        amountInput = findViewById(R.id.amount_input);
        noteInput = findViewById(R.id.note_input);

        amountLayout = findViewById(R.id.amount_input_layout);
        noteLayout = findViewById(R.id.note_input_layout);
        categoryLayout = findViewById(R.id.category_input_layout);
        dateLayout = findViewById(R.id.date_picker_layout);

        datePickerInput.setText(DateConverters.formatDate(new Date(), "dd/MM/yyyy"));

        initializeCategorySelector();
        handleDatePicker();
    }

    private void initializeCategorySelector() {
        List<String> options = new ArrayList<>();

        List<Category> categories = AppDatabase.getInstance(getApplicationContext()).categoryDao().getCategories();
        for (Category category: categories) {
            options.add(category.name);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_category,
                        options);

        dropDownCategory.setAdapter(adapter);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void handleDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        final MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Date date = new Date(selection);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                datePickerInput.setText(format.format(date));
            }
        });

        datePickerInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String fragmentTag = "date_picker";
                FragmentManager fm = getSupportFragmentManager();
                Fragment oldFragment = fm.findFragmentByTag(fragmentTag);

                if (oldFragment != null) return false;
                materialDatePicker.show(getSupportFragmentManager(), fragmentTag);
                return true;
            }
        });
    }

    public void addTransaction(View v) {
        int errorCount = 0;

        int amount;
        String amountString = amountInput.getText().toString();
        FieldValidation amountValidation = new FieldValidation(amountLayout);
        errorCount += amountValidation.required(amountString,"Amount field is required").validate();
        try {
            amount = Integer.parseInt(amountInput.getText().toString());
        } catch (NumberFormatException e) {
            amount = 0;
        }

        String note = noteInput.getText().toString();
        FieldValidation noteValidation = new FieldValidation(noteLayout);
        errorCount += noteValidation.required(note,"Note field is required").validate();

        String category = dropDownCategory.getText().toString();
        FieldValidation categoryValidation = new FieldValidation(categoryLayout);
        errorCount += categoryValidation.required(category,"Category field is required").validate();

        String date = datePickerInput.getText().toString();
        FieldValidation dateValidation = new FieldValidation(dateLayout);
        errorCount += dateValidation.required(date,"Date field is required").validate();

        if (errorCount != 0) {
            return;
        }

        String categoryId = AppDatabase.getInstance(this).categoryDao().findCategoryByName(category).id;

        AppDatabase.getInstance(this).transactionDao().insert(new Transaction(
                amount,
                note,
                DateConverters.transformDateFormat(date, "dd/MM/yyyy"),
                categoryId
        ));

        finish();
    }
}