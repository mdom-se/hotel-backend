package com.demo.hotel.service;

import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotel(HotelDto hotelDto);

    boolean deleteHotel(Long hotelId);

    HotelDto findHotelById(Long id);

    HotelListDto getHotelList(String hotelName, Pageable pageable);

}
