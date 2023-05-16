package com.demo.hotel.service;


import com.demo.hotel.mapper.AmenityMapper;
import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.repository.AmenityRepository;
import com.demo.hotel.repository.HotelAmenityRepository;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    private final HotelAmenityRepository hotelAmenityRepository;

    public AmenityServiceImpl(final AmenityRepository amenityRepository,
                              final HotelAmenityRepository hotelAmenityRepository) {
        this.amenityRepository = amenityRepository;
        this.hotelAmenityRepository = hotelAmenityRepository;
    }


    @Override
    public List<AmenityDto> findAmenitiesByHotelId(Long hotelId) {
        final List<AmenityDto> amenityDtoList;
        List<HotelAmenity> result = hotelAmenityRepository.findHotelsAmenitiesByHotelId(hotelId);
        List<Long> ids = result.stream().map(HotelAmenity::getAmenityId).collect(Collectors.toList());
        if (!ids.isEmpty()) {
            amenityDtoList = amenityRepository.findAllById(ids).stream().map(AmenityMapper::mapToDto).collect(Collectors.toList());
        } else {
            amenityDtoList = Collections.emptyList();
        }
        return amenityDtoList;
    }


}
