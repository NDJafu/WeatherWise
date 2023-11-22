package com.weatherwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weatherwise.Class.Country;
import com.weatherwise.Class.Region;
import com.weatherwise.Class.State;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    ArrayList<Region> regions;
    ArrayList<Country> countries;
    ArrayList<State> states;
    TextView textviewTest;

    public ArrayList<Region> readJsonRegion(Context context, int rawResourceId) {
        try {
            // Mở tệp JSON từ thư mục raw
            InputStream inputStream = context.getResources().openRawResource(rawResourceId);
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Sử dụng Gson để chuyển đổi JSON thành danh sách các đối tượng Region
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Region>>() {}.getType();
            ArrayList<Region> result = gson.fromJson(reader, listType);

            // Đóng luồng đọc
            reader.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Country> readJsonCountry(Context context, int rawResourceId) {
        try {
            // Mở tệp JSON từ thư mục raw
            InputStream inputStream = context.getResources().openRawResource(rawResourceId);
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Sử dụng Gson để chuyển đổi JSON thành danh sách các đối tượng Country
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Country>>() {}.getType();
            ArrayList<Country> result = gson.fromJson(reader, listType);

            // Đóng luồng đọc
            reader.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<State> readJsonState(Context context, int rawResourceId) {
        try {
            // Mở tệp JSON từ thư mục raw
            InputStream inputStream = context.getResources().openRawResource(rawResourceId);
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Sử dụng Gson để chuyển đổi JSON thành danh sách các đối tượng Country
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<State>>() {}.getType();
            ArrayList<State> result = gson.fromJson(reader, listType);

            // Đóng luồng đọc
            reader.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        textviewTest = findViewById(R.id.textviewTest);

        // Đọc danh sách Region từ tệp JSON
        regions = readJsonRegion(this, R.raw.regions);
        countries = readJsonCountry(this, R.raw.countries);
        states = readJsonState(this, R.raw.states);


        // Hiển thị thông tin Region (ví dụ)
//        if (regions != null) {
//            for (Region region : regions) {
//                textviewTest.setText(textviewTest.getText() + region.toString());
//            }
//        }
//        if (countries != null) {
//            for (Country country: countries) {
//                textviewTest.setText(textviewTest.getText() + country.toString());
//            }
//        }
        if (countries != null) {
            for (State state: states) {
                textviewTest.setText(textviewTest.getText() + state.toString());
            }
        }
    }
}