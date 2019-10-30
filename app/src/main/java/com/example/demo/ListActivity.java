package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ListActivity extends AppCompatActivity {
private MapsDAO mapsDAO;
private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvList=findViewById(R.id.rvList);

        mapsDAO=new MapsDAO(ListActivity.this);
        List<Maps> mapsList = mapsDAO.selectMAPS();
       MapsAdapter mapsAdapter = new MapsAdapter(ListActivity.this,mapsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((ListActivity.this));
        rvList.setLayoutManager(linearLayoutManager);
       rvList.setAdapter(mapsAdapter);
    }
}
