package com.infinix.crudapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class activity_recycler_view extends AppCompatActivity {
    RecyclerView recyclerView;
    Cursor cursor;
    DBHelper db;
    ArrayList<DataModel> dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DBHelper(this);
        cursor = db.viewData();

   
        Intent intent = getIntent();
        dataHolder = intent.getParcelableArrayListExtra("data_list");
        MyAdapterClass adapter = new MyAdapterClass(dataHolder, this);
        recyclerView.setAdapter(adapter);
    }

}