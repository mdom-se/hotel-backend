package com.demo.hotel.service;

import com.demo.hotel.dto.HotelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotel(HotelDto hotelDto);

    boolean deleteHotel(Long hotelId);

    List<HotelDto> findHotelsByName(String hotelName);
}
