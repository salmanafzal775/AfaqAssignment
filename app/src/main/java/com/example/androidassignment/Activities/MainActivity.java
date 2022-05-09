package com.example.androidassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidassignment.Adapter.AddressAdapter;
import com.example.androidassignment.Helper.DBHandler;
import com.example.androidassignment.Model.AddressModel;
import com.example.androidassignment.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<AddressModel> addressList;
    private DBHandler dbHandler;
    private AddressAdapter adapter;
    RecyclerView recyclerView;
    Button  add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddAddressActivity.class));
                finish();
            }
        });

    }

    void initViews(){
        addressList = new ArrayList<>();
        dbHandler = new DBHandler(MainActivity.this);

        add = findViewById(R.id.add_address_btn);

        addressList = dbHandler.readAddresses();

        recyclerView = findViewById(R.id.addressListRV);
        adapter = new AddressAdapter(addressList, MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}