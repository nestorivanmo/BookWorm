package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozek.myapplicationfirebasetest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class bookShelfFragment extends Fragment {


    public bookShelfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_shelf, container, false);
    }

}
