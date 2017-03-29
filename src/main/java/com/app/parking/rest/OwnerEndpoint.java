package com.app.parking.rest;

import com.app.parking.model.Owner;
import com.app.parking.repository.OwnerRepository;
import com.app.parking.repository.ParkingAreaRepository;
import com.app.parking.rest.utils.CreateOwner;
import com.app.parking.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by gatomulesei on 3/27/2017.
 */
@RestController
@RequestMapping(value = "/rest/owners")
public class OwnerEndpoint {

    @Autowired
    OwnerService ownerService;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    ParkingAreaRepository parkingAreaRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addOwner(@RequestBody @Valid CreateOwner createOwner){
        final Owner owner = new Owner();
        owner.setOwnerFirstName(createOwner.getOwnerFirstName());
        owner.setOwnerLastName(createOwner.getOwnerLastName());
        owner.setOwnedParkingAreas(createOwner.getOwnedParkingAreas());

        try {
            Owner createdOwner = ownerService.addOwner(owner);
            URI location = URI.create("/rest/owners" + createdOwner.getId());
            return ResponseEntity.created(location).body(createdOwner.getId());
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(new ObjectError("owner.id", exception.getMessage()));
        }
    }

    @RequestMapping(value = "{ownerId}", method = RequestMethod.GET)
    public Owner retrieveOwner(@PathVariable Long ownerId){
        return ownerService.getOwner(ownerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Owner> retrieveAllOwners(){
        return ownerService.getAllOwners();
    }

    @RequestMapping(value = "{ownerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOwner(@PathVariable Long ownerId) {
        ownerService.deleteOwner(ownerId);
        return  ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{ownerId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateOwner(@PathVariable Long ownerId, @RequestBody @Valid CreateOwner createOwner){
        Owner updatedOwner = ownerRepository.findOne(ownerId);
        updatedOwner.setOwnerFirstName(createOwner.getOwnerFirstName());
        updatedOwner.setOwnerLastName(createOwner.getOwnerLastName());
        updatedOwner.setOwnedParkingAreas(createOwner.getOwnedParkingAreas());

        ownerService.addOwner(updatedOwner);
        URI location = URI.create("/rest/owners/" + updatedOwner.getId());
        return ResponseEntity.created(location).build();
    }
}