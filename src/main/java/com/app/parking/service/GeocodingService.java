package com.app.parking.service;

import com.app.parking.model.Address;
import com.app.parking.model.Coordinates;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by gatomulesei on 3/30/2017.
 */
@Service
public class GeocodingService {
    private final GeoApiContext context;

    @Autowired
    public GeocodingService(@Value("${api.key}") String apiKey) {
        this.context = new GeoApiContext()
                .setApiKey(apiKey);
    }

    public Optional<Coordinates> fetchCoordinatesFor(Address address) {
        Objects.requireNonNull(address);

        //log.debug("Fetching coordinates for: " + encode(address));

        final GeocodingResult[] result;

        try {
            result = GeocodingApi.geocode(context, encode(address)).await();
        } catch (Exception e) {
            return Optional.empty();
        }

        return Arrays.stream(result)
                .findFirst()
                .map(r -> r.geometry.location)
                .map(location -> new Coordinates(location.lng, location.lat));
    }

    private static String encode(Address address) {
        return String.format("%s %s %s", address.getStreet(),
                address.getNumber() == null ? "" : address.getNumber(),
                address.getPostcode());
    }
}
