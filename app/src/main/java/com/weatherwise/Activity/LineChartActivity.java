package com.weatherwise.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.weatherwise.R;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineChart = new LineChart(this);
        lineChart.setDrawGridBackground(false);

        // Define X and Y axes
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // Set intervals between X-axis labels

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawGridLines(true);
        yAxis.setLabelCount(YAxis.YAxisLabelPosition.OUTSIDE_CHART.ordinal());

        // Add data to the chart
        LineData lineData = new LineData();
        LineDataSet dataSet = new LineDataSet(prepareData(), "Precipitation");
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        lineData.addDataSet(dataSet);

        lineChart.setData(lineData);


    }

    private List<Entry> prepareData() {
        List<Entry> data = new ArrayList<>();
        data.add(new Entry(11f, 3f));
        data.add(new Entry(12f, 6f));
        data.add(new Entry(13f, 8f));
        data.add(new Entry(14f, 12f));
        data.add(new Entry(15f, 40f));
        return data;
    }








    }

