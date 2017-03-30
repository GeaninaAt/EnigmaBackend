package com.app.parking.service;

import com.app.parking.model.Address;
import com.app.parking.model.ParkingArea;
import com.app.parking.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gatomulesei on 3/30/2017.
 */
@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address addAddress(Address address){
        Address createdAddress = addressRepository.save(address);
        ParkingArea parkingArea = createdAddress.getParkingArea();
        parkingArea.setAddress(address);
        return createdAddress;
    }

    public void deleteAddress(Long addressId){
        Address deletedAddress = addressRepository.findOne(addressId);
        addressRepository.delete(deletedAddress);
    }

    public Address getAddress(Long addressId){
        return addressRepository.findOne(addressId);
    }


}
