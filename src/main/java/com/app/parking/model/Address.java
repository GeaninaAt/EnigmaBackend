package com.app.parking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by gatomulesei on 3/29/2017.
 */
@Entity
public class Address extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private String number;
    private String street;
    private String postcode;

    public Address(String number, String street, String postcode) {
        this.number = number;
        this.street = street;
        this.postcode = postcode;
    }

    @OneToOne
    private ParkingArea parkingArea;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public ParkingArea getParkingArea() {
        return parkingArea;
    }

    public void setParkingArea(ParkingArea parkingArea) {
        this.parkingArea = parkingArea;
    }
}
