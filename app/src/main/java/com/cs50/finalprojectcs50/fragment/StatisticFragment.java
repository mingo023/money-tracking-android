package com.cs50.finalprojectcs50.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.List;

public class StatisticFragment extends Fragment {
    private PieChart pieChart;
    private BarChart barChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        barChart = view.findViewById(R.id.barChart);

        getBarChartData();
        getPieChart();

        return view;
    }

    private void getBarChartData() {
        List<SumAmountTransaction> transactions = AppDatabase.getInstance(getContext()).transactionDao().getTransactions();
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
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int) value);
            }
        });
        barChart.animate();
    }

    private void getPieChart() {
        List<SumTransactionWithCategory> transactions = AppDatabase.getInstance(getContext()).transactionDao().getSumAmountWithCategory();
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
        pieChart.animate();
    }
}