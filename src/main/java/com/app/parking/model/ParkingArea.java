package com.app.parking.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esamedin on 3/26/2017.
 */
@Entity
public class ParkingArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String parkingAreaName;

    @ManyToOne
    private Owner parkingAreaOwner;

    private int parkingLots;

    private String parkingAreaType;

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
}