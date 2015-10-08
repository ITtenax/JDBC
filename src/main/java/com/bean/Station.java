package com.bean;

/**
 * Created by Valentyn on 10/6/2015.
 */
public class Station {
    private String id;
    private String city;
    private String state;
    private String lat_n;
    private String long_w;

    public Station(){
    }

    public Station(String id, String city, String state, String lat_n, String long_w) {
        setId(id);
        setCity(city);
        setState(state);
        setLat_n(lat_n);
        setLong_w(long_w);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLat_n(String lat_n) {
        this.lat_n = lat_n;
    }

    public void setLong_w(String long_w) {
        this.long_w = long_w;
    }

    public String getLong_w() {
        return long_w;
    }

    public String getLan_t() {
        return lat_n;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", lat_n='" + lat_n + '\'' +
                ", long_w='" + long_w + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (!id.equals(station.id)) return false;
        if (!city.equals(station.city)) return false;
        if (!state.equals(station.state)) return false;
        if (!lat_n.equals(station.lat_n)) return false;
        return long_w.equals(station.long_w);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + lat_n.hashCode();
        result = 31 * result + long_w.hashCode();
        return result;
    }
}
