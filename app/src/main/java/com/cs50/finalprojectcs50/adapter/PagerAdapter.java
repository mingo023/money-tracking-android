package com.cs50.finalprojectcs50.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.cs50.finalprojectcs50.activity.MainActivity;
import com.cs50.finalprojectcs50.fragment.PagerFragment;
import com.cs50.finalprojectcs50.model.Category;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
//        List<Category> categories = MainActivity.categoryDatabase.categoryDao().getCategories();
//        String qq = "";
//        for (Category c: categories) {
//            qq += "id = " + c.id + ", name= " + c.name + "\n";
//        }


        Fragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString(PagerFragment.ARG_OBJECT, "qq");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "DAY";
            case 1:
                return "WEEK";
            case 2:
                return "MONTH";
            default:
                return "WRONG";
        }
    }
}
