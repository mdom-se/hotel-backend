package com.demo.hotel.service;

import com.demo.hotel.dto.AmenityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmenityService {

    AmenityDto createAmenity(AmenityDto amenityDto);

    boolean deleteAmenity(Long amenityId);

    List<AmenityDto> findAmenitiesByHotelId(Long hotelId);
}
