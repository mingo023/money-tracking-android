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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityCreateTransaction extends AppCompatActivity {
    private AutoCompleteTextView dropDownCategory;
    private TextInputEditText datePickerInput;
    private TextInputLayout amountInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        dropDownCategory = findViewById(R.id.category_dropdown);
        datePickerInput = findViewById(R.id.date_picker);
        amountInputLayout = findViewById(R.id.amount_input_layout);

        initializeCategorySelector();
        handleDatePicker();
    }

    private void initializeCategorySelector() {
        String[] COUNTRIES = new String[] {"Item 1", "Item 2", "Item 3", "Item 4"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_category,
                        COUNTRIES);

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
}