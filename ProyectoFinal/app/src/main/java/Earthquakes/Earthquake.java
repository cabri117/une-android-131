package Earthquakes;


public class Earthquake {

    public String id;
    public float lng;
    public float lat;
    public float magnitude;

    public Earthquake(String id, float lng, float lat, float magnitude) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.magnitude = magnitude;
    }
}
