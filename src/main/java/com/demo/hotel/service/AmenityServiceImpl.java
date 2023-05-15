package com.demo.hotel.service;


import com.demo.hotel.mapper.AmenityMapper;
import com.demo.hotel.model.Amenity;
import com.demo.hotel.repository.AmenityRepository;
import com.demo.hotel.repository.HotelRepository;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    private final HotelRepository hotelRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository, HotelRepository hotelRepository) {
        this.amenityRepository = amenityRepository;
        this.hotelRepository = hotelRepository;
    }


    @Override
    public AmenityDto createAmenity(AmenityDto amenityDto) {
        AmenityDto result = null;
        if (amenityDto.getHotelId() > 0) {
            boolean hotelExists = hotelRepository.existsById(amenityDto.getHotelId());
            if (hotelExists) {
                Amenity amenity = amenityRepository.save(AmenityMapper.mapToModel(amenityDto));
                result = AmenityMapper.mapToDto(amenity);
            }
        }
        return result;
    }

    @Override
    public boolean deleteAmenity(Long amenityId) {
        boolean deleted = false;
        if (amenityRepository.existsById(amenityId)) {
            amenityRepository.deleteById(amenityId);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public List<AmenityDto> findAmenitiesByHotelId(Long hotelId) {
        List<Amenity> result = amenityRepository.findAmenitiesByHotelId(hotelId);
        return result.stream().map(AmenityMapper::mapToDto).collect(Collectors.toList());
    }
}
