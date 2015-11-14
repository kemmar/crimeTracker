package com.gov.fiirb.crimemapper.domain;

/**
 * Created by ble04 on 13/11/2015.
 */
public class CrimeLocation {

    private Double latitude;

    private Double longitude;

    private Street street;

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
