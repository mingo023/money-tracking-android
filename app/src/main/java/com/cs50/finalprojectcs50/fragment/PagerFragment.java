package com.cs50.finalprojectcs50.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.adapter.TransactionsAdapter;

public class PagerFragment extends Fragment {
    private RecyclerView recyclerView;
    private TransactionsAdapter transactionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.transactions_container, container, false);

        recyclerView = view.findViewById(R.id.transaction_recycler_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        transactionAdapter = new TransactionsAdapter(getContext());
        recyclerView.setAdapter(transactionAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
    }

    @Override
    public void onResume() {
        super.onResume();
        transactionAdapter.loadData();
    }
}
