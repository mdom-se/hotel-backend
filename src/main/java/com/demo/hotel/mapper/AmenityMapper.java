package com.demo.hotel.mapper;

import com.demo.hotel.dto.AmenityDto;
import com.demo.hotel.model.Amenity;

public class AmenityMapper {

    private AmenityMapper(){
        // Utility class
    }

    /**
     *
     * @param amenityModel
     * @return
     */
    public static AmenityDto mapToDto(final Amenity amenityModel){
        return new AmenityDto()
                .setAmenityId(amenityModel.getAmenityId())
                .setAmenityName(amenityModel.getAmenityName())
                .setHotelId(amenityModel.getAmenityId());
    }

    /**
     *
     * @param amenityDto
     * @return
     */
    public static Amenity mapToModel(final AmenityDto amenityDto){
        return new Amenity()
                .setAmenityId(amenityDto.getAmenityId())
                .setAmenityName(amenityDto.getAmenityName())
                .setHotelId(amenityDto.getAmenityId());
    }
}
