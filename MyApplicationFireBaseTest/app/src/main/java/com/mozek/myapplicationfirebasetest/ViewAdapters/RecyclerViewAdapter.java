package com.mozek.myapplicationfirebasetest.ViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mozek.myapplicationfirebasetest.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mBookTitles = new ArrayList<>();
    private ArrayList<String> mBookAuthors = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mBookTitles, ArrayList<String> mBookAuthors) {
        this.mBookTitles = mBookTitles;
        this.mBookAuthors = mBookAuthors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i(TAG, "onCreateViewHolder : called.");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i(TAG, "onBindViewHolder : called.");

        viewHolder.bookTitle.setText(mBookTitles.get(i));
        viewHolder.bookAuthor.setText(mBookAuthors.get(i));


    }

    @Override
    public int getItemCount() {
        return mBookAuthors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookAuthor, bookProgress, currentPage, targetPage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookProgress = itemView.findViewById(R.id.bookProgress);
            currentPage = itemView.findViewById(R.id.currentPage);
            targetPage = itemView.findViewById(R.id.targetPage);
        }
    }
}
