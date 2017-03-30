package com.app.parking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by gatomulesei on 3/29/2017.
 */
@Entity
public class Coordinates extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private double latitude;
    private double longitude;

    @OneToOne
    private ParkingArea parkingArea;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ParkingArea getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(ParkingArea parkingArea) {
        this.parkingArea = parkingArea;
    }
}
