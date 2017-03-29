package com.app.parking.service;

import com.app.parking.model.ParkingArea;
import com.app.parking.repository.ParkingAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by esamedin on 3/26/2017.
 */
@Service("parkingAreaService")
@Transactional
public class ParkingAreaService {

    @Autowired
    ParkingAreaRepository parkingAreaRepository;

    public ParkingArea getParkingArea(long parkingAreaId) {
        return parkingAreaRepository.findOne(parkingAreaId);
    }

    public List<ParkingArea> getAllParkingAreas() {
        return parkingAreaRepository.findAll();
    }

    public ParkingArea addParkingArea(ParkingArea parkingArea) {
        return parkingAreaRepository.save(parkingArea);
    }

    public void deleteParkingArea(Long parkingAreaId) {
        ParkingArea deletedParkingArea = parkingAreaRepository.findOne(parkingAreaId);
        parkingAreaRepository.delete(deletedParkingArea);
    }

}