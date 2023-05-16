package com.demo.hotel.repository;

import com.demo.hotel.model.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Long> {

    List<HotelAmenity> findHotelsAmenitiesByHotelId(Long hotelId);

    long deleteByHotelId(Long hotelId);

    Optional<HotelAmenity> findHotelAmenitiesByHotelIdAndAmenityId(Long hotelId, Long amenityId);

}
