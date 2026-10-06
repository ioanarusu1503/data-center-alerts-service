package org.example;


public class Location {
    private String country;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;

    public Location(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getCountry() {
        return country;
    }
}