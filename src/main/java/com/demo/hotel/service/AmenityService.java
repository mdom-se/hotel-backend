package com.demo.hotel.service;


import com.demo.hotel.webservice.dto.AmenityDto;

import java.util.List;

public interface AmenityService {

    List<AmenityDto> findAmenitiesByHotelId(Long hotelId);

    List<AmenityDto> getAmenityList();

    AmenityDto createAmenity(AmenityDto amenityDto);

    AmenityDto updateAmenity(AmenityDto amenityDto);


}
