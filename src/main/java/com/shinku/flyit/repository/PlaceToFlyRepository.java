package com.shinku.flyit.repository;

import com.shinku.flyit.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceToFlyRepository extends JpaRepository<Place, Integer> {


    @Query(value = "SELECT fip.id, fip.name, fip.location, ST_Distance(fip.location, ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)) AS distance"
    +"FROM flyitdb fip "
    +"ORDER BY fip.location <-> ST_SetRID(ST_Point(:userLongitude,:userLatitude),4326)"
    +"LIMIT 5"
    ,nativeQuery = true)
    List<Place> findPlaceByDistanceFromUser(@Param("userLongitude") Double userLongitude, @Param("userLatitude") Double userLatitude);
}
