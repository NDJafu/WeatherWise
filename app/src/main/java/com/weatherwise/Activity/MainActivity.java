package com.weatherwise.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.weatherwise.Class.Country;
import com.weatherwise.Class.CurrentWeather;
import com.weatherwise.Class.State;
import com.weatherwise.utils.utils;
import com.weatherwise.R;
import com.weatherwise.helper.GsonRequest;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final String CURRENT_WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    final String FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?";
    final String OPENWEATHER_API_KEY = "1b7ce001fbf4143a1618c866d8204c56";
    final String LAT_PARAM = "lat";
    final String LONG_PARAM = "lon";
    final String APIKEY_PARAM = "appid";
    TextView longitudeTextView, latitudeTextView, apiKey;
    RequestQueue queue;
    Uri currentURI, forecastURI;
    private FusedLocationProviderClient fusedLocationProviderClient;

    // Chứa dữ liệu
    ArrayList<Country> countries;
    ArrayList<State> states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitudeTextView = findViewById(R.id.textViewLongitude);
        latitudeTextView = findViewById(R.id.textViewLatitude);
        apiKey = findViewById(R.id.textViewAPI);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        queue = Volley.newRequestQueue(this);

        // Lấy dữ liệu countries và states
        countries = utils.readJson(this, R.raw.countries, Country.class);
        states = utils.readJson(this, R.raw.states, State.class);
        getLocation();
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                currentURI = buildURI(CURRENT_WEATHER_BASE_URL, location);
                forecastURI = buildURI(FORECAST_BASE_URL, location);
                getCurrentWeather();
            } else {
                Toast.makeText(this, "Fail to get location, please allow location or turn on location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Uri buildURI(String url, Location location) {
        return Uri.parse(url)
                .buildUpon()
                .appendQueryParameter(LAT_PARAM, String.valueOf(location.getLatitude()))
                .appendQueryParameter(LONG_PARAM, String.valueOf(location.getLongitude()))
                .appendQueryParameter(APIKEY_PARAM, OPENWEATHER_API_KEY)
                .build();
    }

    private void getCurrentWeather() {
        GsonRequest<CurrentWeather> currentWeatherGsonRequest = new GsonRequest<>(currentURI.toString(), CurrentWeather.class, null, response -> {
            apiKey.setText(response.toString());
        }, error -> {
            Toast.makeText(this, "Fail to fetch weather data, check your internet!", Toast.LENGTH_SHORT).show();
        });
        queue.add(currentWeatherGsonRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}