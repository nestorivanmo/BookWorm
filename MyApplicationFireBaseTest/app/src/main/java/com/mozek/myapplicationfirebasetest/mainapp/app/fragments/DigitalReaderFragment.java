package com.mozek.myapplicationfirebasetest.mainapp.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.mozek.myapplicationfirebasetest.R;

public class DigitalReaderFragment extends Fragment {

    private PDFView pdfView;
    private View view;


    public DigitalReaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_digital_reader, container, false);

        pdfView = view.findViewById(R.id.pdfView);

        if (true){
            pdfView.fromAsset("cien_anios.pdf").load();

        }else{
            pdfView.fromAsset("hombre-busca.pdf").load();
        }

        return view;
    }

}
