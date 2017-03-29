package com.app.parking.repository;

import com.app.parking.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by esamedin on 3/26/2017.
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByOwnerFirstNameAndOwnerLastName(String ownerFirstName, String ownerLastName);
}