package com.app.parking.rest.utils;

import com.app.parking.model.ParkingArea;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gatomulesei on 3/27/2017.
 */
public class CreateOwner {

    @NotNull
    private String ownerFirstName;

    @NotNull
    private String ownerLastName;

    private List<ParkingArea> ownedParkingAreas;

    private long ParkingAreaId;

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public List<ParkingArea> getOwnedParkingAreas() {
        return ownedParkingAreas;
    }

    public void setOwnedParkingAreas(List<ParkingArea> ownedParkingAreas) {
        this.ownedParkingAreas = ownedParkingAreas;
    }

    public long getParkingAreaId() {
        return ParkingAreaId;
    }

    public void setParkingAreaId(long parkingAreaId) {
        ParkingAreaId = parkingAreaId;
    }
}