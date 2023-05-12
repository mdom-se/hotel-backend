package com.demo.hotel.service;


import com.demo.hotel.dto.AmenityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    @Override
    public AmenityDto createAmenity(AmenityDto amenityDto) {
        return null;
    }

    @Override
    public boolean deleteAmenity(Long amenityId) {
        return false;
    }

    @Override
    public List<AmenityDto> findAmenitiesByHotelId(Long hotelId) {
        return null;
    }
}
