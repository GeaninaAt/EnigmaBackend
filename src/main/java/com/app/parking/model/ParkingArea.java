package com.app.parking.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esamedin on 3/26/2017.
 */
@Entity
public class ParkingArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String parkingAreaName;

    private int parkingLots;

    private String parkingAreaType;

    @ManyToOne
    private Owner parkingAreaOwner;

    @OneToOne
    private Address address;

    @OneToOne
    private Coordinates coordinates;

    public ParkingArea(String parkingAreaName, Address address, Coordinates coordinates, Owner owner, int parkingLots, String parkingAreaType) {
        super();
    }

    public ParkingArea() {
    }


    private enum parkingAreaType {
        PUBLIC,
        PRIVATE,
        ON_STREET;

        public static List<String> getAllTypes(){
            List<String> parkingAreaTypes = new ArrayList<>();
            parkingAreaTypes.add(PUBLIC.name());
            parkingAreaTypes.add(PRIVATE.name());
            parkingAreaTypes.add(ON_STREET.name());

            return parkingAreaTypes;
        }
    }

    public String getParkingAreaName() {
        return parkingAreaName;
    }

    public void setParkingAreaName(String parkingAreaName) {
        this.parkingAreaName = parkingAreaName;
    }

    public Owner getParkingAreaOwner() {
        return parkingAreaOwner;
    }

    public void setParkingAreaOwner(Owner parkingAreaOwner) {
        this.parkingAreaOwner = parkingAreaOwner;
    }

    public int getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(int parkingLots) {
        this.parkingLots = parkingLots;
    }

    public String getParkingAreaType() {
        return parkingAreaType;
    }

    public void setParkingAreaType(String parkingAreaType) {
        this.parkingAreaType = parkingAreaType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }



    public static ParkingArea from(ParkingArea parkingArea){

        return new ParkingArea(parkingArea.getParkingAreaName(),
        new Address(parkingArea.getAddress().getNumber(), parkingArea.getAddress().getStreet(), parkingArea.getAddress().getPostcode()),
        new Coordinates(parkingArea.getCoordinates().getLongitude(), parkingArea.getCoordinates().getLatitude()),
        new Owner(parkingArea.getParkingAreaOwner().getOwnerFirstName(), parkingArea.getParkingAreaOwner().getOwnerLastName()),
                parkingArea.getParkingLots(),
                parkingArea.getParkingAreaType());

    }
}