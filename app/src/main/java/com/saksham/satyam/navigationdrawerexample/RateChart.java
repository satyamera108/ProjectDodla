package com.saksham.satyam.navigationdrawerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RateChart extends Fragment {


    public RateChart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_rate_chart, container, false);
        Toast.makeText(RateChart.this.getActivity(),"Under Coding",Toast.LENGTH_SHORT).show();

        return view;
    }

}
