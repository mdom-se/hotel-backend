package com.demo.hotel.service;

import com.demo.hotel.webservice.dto.HotelAmenityDto;

public interface HotelAmenityService {

    HotelAmenityDto addAmenityToHotel(HotelAmenityDto hotelAmenityDto);

    boolean deleteAmenityFromHotel(HotelAmenityDto hotelAmenityDto);

    long deleteHotelAmenitiesByHotelId(Long hotelId);
}
