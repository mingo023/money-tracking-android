package com.cs50.finalprojectcs50.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.adapter.PagerAdapter;
import com.cs50.finalprojectcs50.fragment.PagerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.transition.MaterialContainerTransform;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton addNewTransactionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        addNewTransactionBtn = findViewById(R.id.add_new_transaction_btn);

        addNewTransactionBtn.setOnClickListener(v -> {
            handleBtnClick(v);
        });

        DebugDB.getAddressLog();
    }

    private void handleBtnClick(View v) {
        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
        Intent intent = new Intent(this, ActivityCreateTransaction.class);
        startActivity(intent);
    }
}