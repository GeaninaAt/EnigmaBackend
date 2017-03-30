package com.app.parking.rest.utils;

import com.app.parking.model.Coordinates;

/**
 * Created by gatomulesei on 3/29/2017.
 */
public class CoordinatesUtils {

    public static double distanceBetween(Coordinates c1, Coordinates c2) {
        return Math.hypot(c1.getLatitude() - c2.getLatitude(), c1.getLongitude() - c1.getLongitude());
    }
}
