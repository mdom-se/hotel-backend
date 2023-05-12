package com.demo.hotel.mapper;

import com.demo.hotel.dto.AmenityDto;
import com.demo.hotel.model.Amenity;

public class AmenityMapper {

    private AmenityMapper(){
        // Utility class
    }

    /**
     *
     * @param amenityModel Amenity Model
     * @return AmenityDto
     */
    public static AmenityDto mapToDto(final Amenity amenityModel){
        if(amenityModel == null){
            return null;
        }
        return new AmenityDto()
                .setAmenityId(amenityModel.getAmenityId())
                .setAmenityName(amenityModel.getAmenityName())
                .setHotelId(amenityModel.getAmenityId());
    }

    /**
     *
     * @param amenityDto Amenity DTO
     * @return Amenity Model
     */
    public static Amenity mapToModel(final AmenityDto amenityDto){
        if(amenityDto == null){
            return null;
        }
        return new Amenity()
                .setAmenityId(amenityDto.getAmenityId())
                .setAmenityName(amenityDto.getAmenityName())
                .setHotelId(amenityDto.getAmenityId());
    }
}
