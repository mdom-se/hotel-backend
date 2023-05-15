package com.demo.hotel.service;


import com.demo.hotel.webservice.dto.AmenityDto;

import java.util.List;

public interface AmenityService {

    AmenityDto createAmenity(AmenityDto amenityDto);

    boolean deleteAmenity(Long amenityId);

    List<AmenityDto> findAmenitiesByHotelId(Long hotelId);
}
