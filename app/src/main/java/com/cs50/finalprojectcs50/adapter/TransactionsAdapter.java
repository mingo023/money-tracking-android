package com.cs50.finalprojectcs50.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs50.finalprojectcs50.R;
import com.cs50.finalprojectcs50.database.AppDatabase;
import com.cs50.finalprojectcs50.model.Category;
import com.cs50.finalprojectcs50.model.Transaction;
import com.cs50.finalprojectcs50.model.TransactionAndCategory;
import com.cs50.finalprojectcs50.utils.DateConverters;
import com.cs50.finalprojectcs50.utils.StringConverts;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {
    public List<TransactionAndCategory> dataSets;

    private Context context;

    public TransactionsAdapter(Context context) {
        this.context = context;
        this.loadData();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        public TextView amountText;
        public TextView noteText;
        public TextView dateText;
        public TextView categoryText;

        public TransactionViewHolder(@NonNull View view) {
            super(view);

            amountText = view.findViewById(R.id.amount_text);
            noteText = view.findViewById(R.id.note_text);
            categoryText = view.findViewById(R.id.category_text);
            dateText = view.findViewById(R.id.date_text);
        }
    }


    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_row, parent, false);

        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = dataSets.get(position).transaction;
        Category category = dataSets.get(position).category;

        holder.amountText.setText(StringConverts.formatCurrency(transaction.amount));
        holder.noteText.setText(transaction.note);
        holder.categoryText.setText(category.name);
        holder.dateText.setText(DateConverters.formatDate(transaction.date, "dd/MM/yyyy"));
    }

    @Override
    public int getItemCount() {
        return dataSets.size();
    }

    public void loadData() {
        this.dataSets = AppDatabase.getInstance(context).transactionDao().getTransactionsAndCategory();
        notifyDataSetChanged();
    }

    public void loadData(long start, long end) {
        this.dataSets = AppDatabase.getInstance(context).transactionDao().getTransactionsAndCategory(start, end);
        notifyDataSetChanged();
    }
}
