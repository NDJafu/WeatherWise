package com.weatherwise.Class;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("region_id")
    private String region_id;

    public Country(int id, String name, String region_id) {
        this.id = id;
        this.name = name;
        this.region_id = region_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegionId() {
        return region_id;
    }

    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region_id + '\'' +
                '}';
    }
}
