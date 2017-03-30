package com.app.parking.repository;

import com.app.parking.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gatomulesei on 3/29/2017.
 */
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
