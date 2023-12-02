package com.weatherwise.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weatherwise.Adapter.HourlyAdapter;
import com.weatherwise.Domains.Hourly;
import com.weatherwise.R;

import java.util.ArrayList;

public class Main_Screen extends AppCompatActivity {
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main_screen);

        initRecyclerView();
        setVariable();
    }

    private void setVariable() {
        TextView next7dayBtn=findViewById(R.id.nextBtn);
        next7dayBtn.setOnClickListener(view -> startActivity(new Intent(Main_Screen.this,TommorowActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<Hourly> items=new ArrayList<>();

        items.add(new Hourly("10 pm",Integer.parseInt("28"),"cloudy"));
        items.add(new Hourly("11 pm",Integer.parseInt("28"),"sun"));
        items.add(new Hourly("12 pm",Integer.parseInt("28"),"wind"));
        items.add(new Hourly("1 pm",Integer.parseInt("28"),"rainy"));
        items.add(new Hourly("12 pm",Integer.parseInt("28"),"storm"));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        adapterHourly = new HourlyAdapter(items);

        recyclerView.setAdapter(adapterHourly);
    }
}