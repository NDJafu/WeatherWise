package com.weatherwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.weatherwise.R;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineChart = (LineChart) findViewById(R.id.linechart);

        ArrayList<String> xAes = new ArrayList<>();
        ArrayList<Entry> yAesin = new ArrayList<>();
        ArrayList<Entry> yAecos = new ArrayList<>();
        double x = 0;
        int numDataPoint = 1000;
        for ( int i = 0; i < numDataPoint ; i++){
            float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
            float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
            x = x+ 0.1;
            yAesin.add(new Entry(sinFunction,i));
            yAecos.add(new Entry(cosFunction,i));
            xAes.add(i , String.valueOf(x));
        }
        String[] xaxes = new String[xAes.size()];
        for(int i = 0; i<xAes.size(); i++){
            xaxes[i] = xAes.get(i).toString();
        }

        ArrayList< LineDataSet> lineDataSet = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAecos,"cos");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.WHITE);

        LineDataSet lineDataSet2 = new LineDataSet(yAesin,"sin");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.GREEN);

        lineDataSet.add(lineDataSet1);
        lineDataSet.add(lineDataSet2);



    }

}