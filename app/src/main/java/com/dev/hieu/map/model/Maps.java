package com.dev.hieu.map.model;

public class Maps {
    public String id;
    public String lat;
    public String lng;

    public Maps(String id, String lat, String lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }
    public Maps() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
