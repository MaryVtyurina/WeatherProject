package model;

/**
 * Created by Maria on 11/12/16.
 */
public class WeekPlace {
    private float lon;
    private float lat;
    private String city;
    private String country;
    private long data;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
