package com.shinku.flyit.service.impl;

import com.shinku.flyit.entity.Place;
import com.shinku.flyit.repository.PlaceToFlyRepository;
import com.shinku.flyit.service.PlaceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlaceServiceImp implements PlaceService {

    @Autowired
    private PlaceToFlyRepository placeToFlyRepository;

    @Override
    public List<Place> findAll() {
        return placeToFlyRepository.findAll();
    }

    @Override
    public Place findById(int id) throws NotFoundException {
        return placeToFlyRepository.findById(id).orElseThrow(
            () -> new NotFoundException("The place with ID: "+id+" not found")
        );
    }

    @Override
    public List<Place> findPlaceByDistanceFromUser(Double userLongitude, Double userLatitude) {
        return placeToFlyRepository.findPlaceByDistanceFromUser(userLongitude,userLatitude);
    }


    @Override
    public Place save(Place place){
        return placeToFlyRepository.save(place);
    }
    @Override
    public void deleteById(int id) {
        placeToFlyRepository.deleteById(id);
    }

}
