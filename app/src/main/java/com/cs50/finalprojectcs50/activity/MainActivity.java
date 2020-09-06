package com.cs50.finalprojectcs50.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.amitshekhar.DebugDB;
import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.fragment.HomeFragment;
import com.cs50.finalprojectcs50.fragment.StatisticFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialContainerTransform;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Default for fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> onChangeItemBottomNav(item));

        DebugDB.getAddressLog();
    }

    private boolean onChangeItemBottomNav(MenuItem item) {
        Fragment selectedFragment = new HomeFragment();
        switch (item.getItemId()) {
            case R.id.page_1:
                selectedFragment = new HomeFragment();
                break;
            case R.id.page_2:
                selectedFragment = new StatisticFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;

    }

}