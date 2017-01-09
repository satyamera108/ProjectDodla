package com.saksham.satyam.navigationdrawerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Test2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Test2 extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Test2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Test2.
     */
    // TODO: Rename and change types and number of parameters
    public static Test2 newInstance(String param1, String param2) {
        Test2 fragment = new Test2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,mParam1);
        Log.d(TAG,mParam2);

        View view = inflater.inflate(R.layout.fragment_test2, container, false);

        final ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("Satya",20,55.45);
        Product p2 = new Product("Venkat",22,45.55);
        Product p3 = new Product("Narayan",21,65.55);
        products.add(p1);
        products.add(p2);
        products.add(p3);

        BindDictionary<Product> dictionary = new BindDictionary<>();
        dictionary.addStringField(R.id.tvName, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return product.getName();
            }
        });

        dictionary.addStringField(R.id.tvAge, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return "" + product.getAge();
            }
        });

        dictionary.addStringField(R.id.tvWeight, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return "" + product.getWeight();
            }
        });
        FunDapter adapter = new FunDapter(Test2.this.getActivity(), products, R.layout.prodlayout,dictionary);
       // ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(Test2.this.getActivity(),android.R.layout.simple_list_item_1,products);
        ListView listv = (ListView)view.findViewById(R.id.list);
        listv.setAdapter(adapter);
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product seleProduct = products.get(i);
                Toast.makeText(Test2.this.getActivity(),seleProduct.getName(),Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }

}
