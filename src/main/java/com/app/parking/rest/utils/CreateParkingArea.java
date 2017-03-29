package com.app.parking.rest.utils;

import javax.validation.constraints.NotNull;

/**
 * Created by gatomulesei on 3/27/2017.
 *
 * json for create parking area operation
 */
public class CreateParkingArea {

    @NotNull
    private String parkingAreaName;

    @NotNull
    private Long ownerId;

    private int parkingLots;

    @NotNull
    private String parkingAreaType;

    public String getParkingAreaName() {
        return parkingAreaName;
    }

    public void setParkingAreaName(String parkingAreaName) {
        this.parkingAreaName = parkingAreaName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
