package com.app.parking.repository;

import com.app.parking.model.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by esamedin on 3/26/2017.
 */
public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Long> {

    ParkingArea findByParkingAreaName(String parkingAreaName);
}
