package com.cs50.finalprojectcs50.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.SumAmountTransaction;
import com.cs50.finalprojectcs50.model.SumTransactionWithCategory;
import com.cs50.finalprojectcs50.utils.DateConverters;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticFragment extends Fragment {
    private PieChart pieChart;
    private BarChart barChart;
    private TextInputEditText startDatePicker;
    private TextInputEditText endDatePicker;
    private Date startPickedDate;
    private Date endPickedDate;

    private Date startOfMonth;
    private Date endOfMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        barChart = view.findViewById(R.id.barChart);
        startDatePicker = view.findViewById(R.id.date_start);
        endDatePicker = view.findViewById(R.id.date_end);

        startOfMonth = DateConverters.startOfMonth();
        endOfMonth = DateConverters.endOfMonth();
        startPickedDate = startOfMonth;
        endPickedDate = endOfMonth;

        System.out.println(startOfMonth.getTime());
        System.out.println(endOfMonth.getTime());

        startDatePicker.setText(DateConverters.formatDate(startOfMonth, "dd/MM/yyyy"));
        endDatePicker.setText(DateConverters.formatDate(endOfMonth, "dd/MM/yyyy"));

        getBarChartData(startOfMonth.getTime(), endOfMonth.getTime());
        getPieChart(startOfMonth.getTime(), endOfMonth.getTime());

        handleStartDatePicker();
        handleEndDatePicker();

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void handleStartDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();;
        final MaterialDatePicker materialDatePicker= builder.build();

        materialDatePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Long>) selection -> {
            startPickedDate = new Date(selection);
            startDatePicker.setText(DateConverters.formatDate(startPickedDate, "dd/MM/yyyy"));
            handleFetchChart(startPickedDate, endPickedDate);
        });

        startDatePicker.setOnTouchListener((v, event) -> showCalender(materialDatePicker, "date_picker_start"));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void handleEndDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();;
        final MaterialDatePicker materialDatePicker= builder.build();

        materialDatePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Long>) selection -> {
            endPickedDate = new Date(selection);
            endDatePicker.setText(DateConverters.formatDate(endPickedDate, "dd/MM/yyyy"));
            handleFetchChart(startPickedDate, endPickedDate);
        });

        endDatePicker.setOnTouchListener((v, event) -> showCalender(materialDatePicker, "date_picker_end"));
    }

    private void handleFetchChart(Date start, Date end) {
        if (start == null || end == null) return;

        getBarChartData(start.getTime(), end.getTime());
        barChart.notifyDataSetChanged();
        barChart.invalidate();

        getPieChart(start.getTime(), end.getTime());
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }

    private boolean showCalender(MaterialDatePicker materialDatePicker, String fragmentTag) {
        FragmentManager fm = getFragmentManager();
        Fragment oldFragment = fm.findFragmentByTag(fragmentTag);

        if (oldFragment != null) return false;
        if (!materialDatePicker.isAdded()) {
            materialDatePicker.show(getFragmentManager(), fragmentTag);
        }
        return true;
    }

    private void getBarChartData(long start, long end) {
        List<SumAmountTransaction> transactions = AppDatabase.getInstance(getContext()).transactionDao().getGroupedTransactions(start, end);
        List<BarEntry> transactionsChart = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < transactions.size(); ++i) {
            SumAmountTransaction transaction = transactions.get(i);

            transactionsChart.add(new BarEntry(i, transaction.amount));
            labels.add(DateConverters.formatDate(transaction.date, "dd/MM"));
        }

        BarDataSet barDataSet = new BarDataSet(transactionsChart, "Transactions");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.getXAxis().setCenterAxisLabels(false);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if ((int) value >= labels.size()) return "";
                return labels.get((int) value);
            }
        });

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getDescription().setEnabled(false);
        barChart.animate();
    }

    private void getPieChart(long start, long end) {
        List<SumTransactionWithCategory> transactions = AppDatabase.getInstance(getContext()).transactionDao().getSumAmountWithCategory(start, end);
        List<PieEntry> categoryChart = new ArrayList<>();
        for (int i = 0; i < transactions.size(); ++i) {
            SumTransactionWithCategory transaction = transactions.get(i);

            categoryChart.add(new PieEntry(transaction.amount, transaction.categoryName));
        }

        PieDataSet pieDataSet = new PieDataSet(categoryChart, "Category");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
    }
}