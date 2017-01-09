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
public class FarmerReg extends Fragment {


    public FarmerReg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_farmer_reg, container, false);
        Toast.makeText(FarmerReg.this.getActivity(),"Under Coding",Toast.LENGTH_SHORT).show();

        return view;
    }

}
