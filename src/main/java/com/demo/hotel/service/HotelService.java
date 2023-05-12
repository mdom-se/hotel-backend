package com.demo.hotel.service;

import com.demo.hotel.dto.HotelDto;
import com.demo.hotel.dto.HotelListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotel(HotelDto hotelDto);

    boolean deleteHotel(Long hotelId);

    HotelDto findHotelById(Long id);

    List<HotelDto> findHotelsByName(String hotelName, Pageable pageable);

    HotelListDto getHotelList(Pageable pageable);
}
