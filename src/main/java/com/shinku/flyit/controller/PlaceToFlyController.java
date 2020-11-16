package com.shinku.flyit.controller;

import com.shinku.flyit.entity.Place;
import com.shinku.flyit.service.PlaceService;
import javassist.NotFoundException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shinku.flyit.utils.AppConstants.PLACE_JSON_PARAM;

@RestController
@CrossOrigin(origins="http://localhost:8083")
public class PlaceToFlyController {

    @Autowired
    private PlaceService placeService;

    @GetMapping(value = "/api/place")
    public List<Place> findAll(){
        return placeService.findAll();
    }

    @RequestMapping(value="/api/place/{id}", method = RequestMethod.GET)
    public Place findById(@PathVariable int id){
        try {
            return placeService.findById(id);
        } catch (NotFoundException e){
            return null;
        }
    }

    @GetMapping("/api/nearplace")
    public List<Place> findAllPlaceByDistanceFromUser(@RequestParam("userLocation") List<Double> userLocation){
        double userLongitude = 0.0;
        double userLatitude = 0.0;
        if(userLocation.size() == 2){
            userLongitude = userLocation.get(0);
            userLatitude = userLocation.get(1);
        }
        return placeService.findPlaceByDistanceFromUser(userLongitude, userLatitude);
    }

    @RequestMapping(value = "/api/placetofly", method = RequestMethod.POST)
    public ResponseEntity<?> addPlace(@RequestBody Place place){
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate coordinate = new Coordinate();
        coordinate.x = place.getLongitude();
        coordinate.y = place.getLatitude();

        Point myPoint = geometryFactory.createPoint(coordinate);
        place.setLocation(myPoint);

        return ResponseEntity.ok(placeService.save(place));

    }
    @RequestMapping(value = "/api/place/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable int id){
        placeService.deleteById(id);
    }
}
