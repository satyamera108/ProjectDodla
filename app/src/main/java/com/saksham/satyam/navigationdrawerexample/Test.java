package com.saksham.satyam.navigationdrawerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import tw.com.prolific.pl2303multilib.PL2303MultiLib;



/**
 * A simple {@link Fragment} subclass.
 */
public class Test extends Fragment {


    public Test() {
        // Required empty public constructor
    }
    EditText TextIn;

    EditText Fn;
    private MyDBHandler dbHandler;
    TextView TextOut;

    @Override

    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new MyDBHandler(Test.this.getActivity(),null,null,1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextIn = (EditText)view.findViewById(R.id.Fcode);
        Fn = (EditText)view.findViewById(R.id.Fname);
        TextOut = (TextView)view.findViewById(R.id.Output);
        dbHandler = new MyDBHandler(Test.this.getActivity(),null,null,1);
        final View adddata = view.findViewById(R.id.addButton);
        final View deldata = view.findViewById(R.id.deleteButton);
        adddata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                dbHandler = new MyDBHandler(Test.this.getActivity(),null,null,1);
                Products product = new Products(TextIn.getText().toString(),Fn.getText().toString());
                    dbHandler.addProduct(product);
                    printDatabase();
                    Toast.makeText(Test.this.getActivity(),"Record Added",Toast.LENGTH_SHORT).show();
                }
        });
        deldata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                dbHandler = new MyDBHandler(Test.this.getActivity(),null,null,1);
                String inputtxt = TextIn.getText().toString();
                dbHandler.deleteProduct(inputtxt);
                printDatabase();
                Toast.makeText(Test.this.getActivity(),"Record Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
   /* public void addButtonClicked(View view){
        Products product = new Products(TextIn.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }
    public void deleteButtonClicked(View view){
        String inputtxt = TextIn.getText().toString();
        dbHandler.deleteProduct(inputtxt);
        Toast.makeText(Test.this.getActivity(), "Deleted", Toast.LENGTH_SHORT).show();

        printDatabase();
    }*/
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        TextOut.setText(dbString);
        TextIn.setText("");
        Fn.setText("");
    }

}
