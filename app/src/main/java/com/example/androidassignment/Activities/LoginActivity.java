package com.example.androidassignment.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.androidassignment.Helper.DBHandler;
import com.example.androidassignment.R;

public class LoginActivity extends AppCompatActivity {
    AppCompatEditText nameET, addressET, passwordET;
    Button login;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameET.getText().toString().isEmpty()) {
                    nameET.setError("Please enter name");
                } else if (addressET.getText().toString().isEmpty()) {
                    addressET.setError("Please enter address");
                } else if (passwordET.getText().toString().isEmpty()) {
                    passwordET.setError("Please enter password");
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        String s2 = sh.getString("address", "");
        String s3 = sh.getString("password", "");

        nameET.setText(s1);
        addressET.setText(s2);
        passwordET.setText(s3);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("name", nameET.getText().toString());
        myEdit.putString("address", addressET.getText().toString());
        myEdit.putString("password", passwordET.getText().toString());
        myEdit.apply();
    }

    void initViews() {
        nameET = findViewById(R.id.nameET);
        addressET = findViewById(R.id.addressET);
        passwordET = findViewById(R.id.passwordET);
        login = findViewById(R.id.login_btn);
        dbHandler = new DBHandler(LoginActivity.this);
    }
}