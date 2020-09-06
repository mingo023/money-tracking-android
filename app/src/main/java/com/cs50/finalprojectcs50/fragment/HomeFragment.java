package com.cs50.finalprojectcs50.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.activity.CreateTransactionActivity;
import com.cs50.finalprojectcs50.adapter.PagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.transition.MaterialContainerTransform;

public class HomeFragment extends Fragment {
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton addNewTransactionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pagerAdapter = new PagerAdapter(getFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        addNewTransactionBtn = view.findViewById(R.id.add_new_transaction_btn);
        addNewTransactionBtn.setOnClickListener(v -> {
            handleOnClickFab(v);
        });

        return view;
    }
    private void handleOnClickFab(View v) {
        Intent intent = new Intent(getContext(), CreateTransactionActivity.class);
        startActivity(intent);
    }

}