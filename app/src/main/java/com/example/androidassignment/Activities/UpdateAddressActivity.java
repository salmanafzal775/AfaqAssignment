package com.example.androidassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidassignment.Helper.DBHandler;
import com.example.androidassignment.R;

public class UpdateAddressActivity extends AppCompatActivity {

    AppCompatEditText addressET, descriptionET;
    DBHandler dbHandler;
    Button updateBtn, deleteBtn;
    String address, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        initViews();

        address = getIntent().getStringExtra("address");
        description = getIntent().getStringExtra("description");

        addressET.setText(address);
        descriptionET.setText(description);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.updateAddress(address, addressET.getText().toString(), descriptionET.getText().toString());
                Toast.makeText(UpdateAddressActivity.this, "Address Updated...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateAddressActivity.this, MainActivity.class));
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteAddress(address);
                Toast.makeText(UpdateAddressActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateAddressActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    void initViews(){
        addressET = findViewById(R.id.addressET);
        descriptionET = findViewById(R.id.descriptionET);
        dbHandler = new DBHandler(UpdateAddressActivity.this);
        updateBtn = findViewById(R.id.update_address);
        deleteBtn = findViewById(R.id.delete_address);
    }
}