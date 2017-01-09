package com.saksham.satyam.navigationdrawerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Spinner roles;
    EditText uname,password;
    Button validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        roles = (Spinner)findViewById(R.id.role_select);
        uname = (EditText)findViewById(R.id.uname_input);
        password = (EditText)findViewById(R.id.pwd_input);
        validate = (Button)findViewById(R.id.login_button);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uname.getText().toString().equals("Satya")&&
                        password.getText().toString().equals("Satya")&&
                        roles.getSelectedItem().toString().equals("Admin")){
                        Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
