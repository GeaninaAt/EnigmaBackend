package com.app.parking.service;

import com.app.parking.model.Coordinates;
import com.app.parking.repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gatomulesei on 3/30/2017.
 */
@Service
@Transactional
public class CoordinatesService {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    public Coordinates addCoordinates(Coordinates coordinates){
        return coordinatesRepository.save(coordinates);
    }


}
