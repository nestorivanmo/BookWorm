package com.mozek.myapplicationfirebasetest.ViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozek.myapplicationfirebasetest.R;

import java.util.ArrayList;


public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<String> mBookTitles = new ArrayList<>();
    private ArrayList<String> mBookAuthors = new ArrayList<>();
    private ArrayList<String> mBookProgress = new ArrayList<>();

    public LibraryRecyclerViewAdapter(Context mContext, ArrayList<String> mBookTitles, ArrayList<String> mBookAuthors, ArrayList<String> mBookProgress) {
        this.mContext = mContext;
        this.mBookTitles = mBookTitles;
        this.mBookAuthors = mBookAuthors;
        this.mBookProgress = mBookProgress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_library_list_item, viewGroup, false);
        return new LibraryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bookTitle.setText(mBookTitles.get(i));
        viewHolder.bookAuthor.setText(mBookAuthors.get(i));
        viewHolder.bookProgress.setText(mBookProgress.get(i));
    }

    @Override
    public int getItemCount() {
        return mBookAuthors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView bookTitle, bookAuthor, bookProgress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.libro);
            bookAuthor = itemView.findViewById(R.id.autor);
            bookProgress = itemView.findViewById(R.id.progreso);
        }
    }
}
