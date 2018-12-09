package com.dev.hieu.map.model;

public class Maps {
//    public String id;
    public double lat;
    public double lng;

    public Maps(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public  Maps() {

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
