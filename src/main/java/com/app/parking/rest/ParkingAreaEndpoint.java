package com.app.parking.rest;

import com.app.parking.model.Coordinates;
import com.app.parking.model.ParkingArea;
import com.app.parking.repository.OwnerRepository;
import com.app.parking.repository.ParkingAreaRepository;
import com.app.parking.rest.utils.CreateParkingArea;
import com.app.parking.service.GeocodingService;
import com.app.parking.service.ParkingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static com.app.parking.rest.utils.CoordinatesUtils.distanceBetween;

/**
 * Created by gatomulesei on 3/27/2017.
 */
@RestController
@RequestMapping("/rest/parkingAreas")
public class ParkingAreaEndpoint {

    @Autowired
    GeocodingService geocodingService;

    @Autowired
    private ParkingAreaService parkingAreaService;

    @Autowired
    private ParkingAreaRepository parkingAreaRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    public ParkingAreaEndpoint(GeocodingService geocodingService, ParkingAreaRepository parkingAreaRepository){
        this.geocodingService = geocodingService;
        this.parkingAreaRepository = parkingAreaRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addParkingArea(@RequestBody @Valid CreateParkingArea createParkingArea){
        if(parkingAreaRepository.exists(createParkingArea.getOwnerId())){
            return ResponseEntity.badRequest().body(new ObjectError("parkingArea.owner", "Invalid owner id."));
        }

        final ParkingArea parkingArea = new ParkingArea();
        parkingArea.setParkingAreaName(createParkingArea.getParkingAreaName());
        parkingArea.setParkingLots(createParkingArea.getParkingLots());
        parkingArea.setParkingAreaOwner(ownerRepository.findOne(createParkingArea.getOwnerId()));
        parkingArea.setParkingAreaType(createParkingArea.getParkingAreaType());

        try{
            ParkingArea createdParkingArea = parkingAreaService.addParkingArea(parkingArea);
            URI location = URI.create("/rest/parkingAreas/" + createdParkingArea.getId());
            return ResponseEntity.created(location).body(createdParkingArea.getId());
        } catch(UnsupportedOperationException exception){
            return ResponseEntity.badRequest().body(new ObjectError("parkingArea.id", exception.getMessage()));
        }
    }

    @RequestMapping(value = "{parkingAreaId}", method = RequestMethod.GET)
    public ParkingArea retrieveParkingArea(@PathVariable Long parkingAreaId){
        return parkingAreaService.getParkingArea(parkingAreaId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ParkingArea> retrieveAllParkingAreas(){
        return parkingAreaService.getAllParkingAreas();
    }

    @RequestMapping(value = "{parkingAreaId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteParkingArea(@PathVariable Long parkingAreaId){
        parkingAreaService.deleteParkingArea(parkingAreaId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{parkingAreaId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateParkingArea(@PathVariable Long parkingAreaId, @RequestBody @Valid CreateParkingArea createParkingArea) {
        ParkingArea updatedParkingArea = parkingAreaRepository.findOne(parkingAreaId);

        updatedParkingArea.setParkingAreaName(createParkingArea.getParkingAreaName());
        updatedParkingArea.setParkingLots(createParkingArea.getParkingLots());

        updatedParkingArea.setParkingAreaOwner(ownerRepository.findOne(createParkingArea.getOwnerId()));
        updatedParkingArea.setParkingAreaType(createParkingArea.getParkingAreaType());

        parkingAreaService.addParkingArea(updatedParkingArea);
        URI location = URI.create("/rest/parkingAreas/" + updatedParkingArea.getId());
        return ResponseEntity.created(location).build();
    }


    @RequestMapping(value = "/nearest", method = RequestMethod.GET)
    @ResponseBody
    public Optional<ParkingArea> getNearest(@RequestParam double longitude, @RequestParam double latitude){
        return parkingAreaRepository.findAll().stream()
                .reduce(byDistanceTo(longitude, latitude))
                .map(ParkingArea::from);
    }

    private static BinaryOperator<ParkingArea> byDistanceTo(double longitude, double latitude) {
        return (s1, s2) -> {
            final double s1Distance = distanceBetween(s1.getCoordinates(), new Coordinates(longitude, latitude));
            final double s2Distance = distanceBetween(s2.getCoordinates(), new Coordinates(longitude, latitude));

            return s1Distance < s2Distance ? s1 : s2;
        };
    }

}
