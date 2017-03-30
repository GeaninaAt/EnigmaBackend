package com.app.parking.service;

import com.app.parking.model.Owner;
import com.app.parking.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by esamedin on 3/26/2017.
 */
@Service("ownerService")
@Transactional
public class OwnerService {

    //private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OwnerService.class);

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner addOwner(Owner owner){
            return ownerRepository.save(owner);
    }

    public void deleteOwner(Long ownerId){
        Owner deletedOwner = ownerRepository.findOne(ownerId);
        ownerRepository.delete(deletedOwner);
    }

    public Owner getOwner(Long ownerId){
        return ownerRepository.findOne(ownerId);
    }

    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }

}