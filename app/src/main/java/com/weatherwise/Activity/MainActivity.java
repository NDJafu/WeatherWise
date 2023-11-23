package com.weatherwise.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
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
import com.weatherwise.Class.CurrentWeather;
import com.weatherwise.R;

import com.weatherwise.helper.GsonRequest;
import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {
    TextView longitudeTextView, latitudeTextView, apiKey;
    private FusedLocationProviderClient fusedLocationProviderClient;
    final String CURRENT_WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    final String FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?";
    final String OPENWEATHER_API_KEY = "1b7ce001fbf4143a1618c866d8204c56";
    final String LAT_PARAM = "lat";
    final String LONG_PARAM = "lon";
    final String APIKEY_PARAM = "appid";
    RequestQueue queue;

    Uri builtUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitudeTextView = findViewById(R.id.textViewLongitude);
        latitudeTextView = findViewById(R.id.textViewLatitude);
        apiKey = findViewById(R.id.textViewAPI);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        queue = Volley.newRequestQueue(this);

        getWeather(OPENWEATHER_API_KEY);
    }


    private void getWeather(String api_key) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                builtUri = Uri.parse("https://api.openweathermap.org/data/2.5/weather?")
                        .buildUpon()
                        .appendQueryParameter(LAT_PARAM, String.valueOf(location.getLatitude()))
                        .appendQueryParameter(LONG_PARAM, String.valueOf(location.getLongitude()))
                        .appendQueryParameter(APIKEY_PARAM, api_key)
                        .build();
                GsonRequest<CurrentWeather> currentWeatherGsonRequest = new GsonRequest<>("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=1b7ce001fbf4143a1618c866d8204c56", CurrentWeather.class, null, response -> {
                    apiKey.setText(response.toString());
                }, error -> {
                    apiKey.setText("Error!:" + error.toString());
                });
                queue.add(currentWeatherGsonRequest);
            } else {
                longitudeTextView.setText("Nothing");
            }
        });
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