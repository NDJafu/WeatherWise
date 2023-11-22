package com.weatherwise.Class;

public class Region {
    private int id;
    private String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
