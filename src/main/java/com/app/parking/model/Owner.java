package com.app.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by esamedin on 3/26/2017.
 */
@Entity
public class Owner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String ownerFirstName;

    private String ownerLastName;

    public Owner(String ownerFirstName, String ownerLastName) {
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public Owner(){}

    @OneToMany(mappedBy = "parkingAreaOwner")
    @JsonIgnore
    private List<ParkingArea> ownedParkingAreas;

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
}
