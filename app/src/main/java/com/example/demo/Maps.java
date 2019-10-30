package com.example.demo;

public class Maps {

private String Title;
private Double longtitude;
private Double latitude;

    public Maps() {
    }

    public Maps(String title, Double longtitude, Double latitude) {
        this.Title = title;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
