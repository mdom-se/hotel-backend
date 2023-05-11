package com.demo.hotel.repository;

import com.demo.hotel.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findAmenitiesByHotelId(Long hotelId);
}
