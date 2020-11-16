package com.shinku.flyit.service;

import com.shinku.flyit.entity.Place;
import com.shinku.flyit.repository.PlaceToFlyRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PlaceService {


    List<Place> findAll();
    Place findById(int id) throws NotFoundException;
    List<Place> findPlaceByDistanceFromUser(Double userLongitude, Double userLatitude);
    void deleteById(int id);
    Place save(Place place);
}
