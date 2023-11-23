package com.weatherwise.Class;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class CurrentWeather {
    @SerializedName("coord")
    private Coordinate coordinate;
    @SerializedName("weather")
    public Weather[] weathers;
    public MainInformation main;
    public int visibility;
    public Wind wind;
    public Rain rain;
    public Cloud clouds;
    @SerializedName("dt")
    public long dateTime;
    public int timezone;
    @SerializedName("id")
    public int cityID;
    @SerializedName("name")
    public String cityName;


    public static class Wind {
        @SerializedName("speed")
        public double windSpeed;
        @SerializedName("deg")
        public int windDirection;
        @SerializedName("gust")
        public double windGust;

        public Wind(double windSpeed, int windDirection, double windGust) {
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
            this.windGust = windGust;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "windSpeed=" + windSpeed +
                    ", windDirection=" + windDirection +
                    ", windGust=" + windGust +
                    '}';
        }
    }

    public static class Rain {
        @SerializedName("1h")
        public double lastOneHourVolume;

        public Rain(double lastOneHourVolume) {
            this.lastOneHourVolume = lastOneHourVolume;
        }

        @Override
        public String toString() {
            return "Rain{" +
                    "lastOneHourVolume=" + lastOneHourVolume +
                    '}';
        }
    }

    public static class Cloud {
        @SerializedName("all")
        public int cloudiness;

        public Cloud(int cloudiness) {
            this.cloudiness = cloudiness;
        }

        @Override
        public String toString() {
            return "Cloud{" +
                    "cloudiness=" + cloudiness +
                    '}';
        }
    }

    public CurrentWeather(Coordinate coordinate, Weather[] weathers, MainInformation main, int visibility, Wind wind, Rain rain, Cloud clouds, long dateTime, int timezone, int cityID, String cityName) {
        this.coordinate = coordinate;
        this.weathers = weathers;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dateTime = dateTime;
        this.timezone = timezone;
        this.cityID = cityID;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "coordinate=" + coordinate +
                ", weathers=" + Arrays.toString(weathers) +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dateTime=" + dateTime +
                ", timezone=" + timezone +
                ", cityID=" + cityID +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
