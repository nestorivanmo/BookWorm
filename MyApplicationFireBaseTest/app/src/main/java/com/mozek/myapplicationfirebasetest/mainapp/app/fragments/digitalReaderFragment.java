package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozek.myapplicationfirebasetest.R;

public class DigitalReaderFragment extends Fragment {

    public DigitalReaderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_digital_reader, container, false);
    }

}
