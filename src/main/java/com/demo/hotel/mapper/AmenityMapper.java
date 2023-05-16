package com.demo.hotel.mapper;

import com.demo.hotel.model.Amenity;
import com.demo.hotel.webservice.dto.AmenityDto;

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
        AmenityDto amenityDto = new AmenityDto();
        amenityDto.setAmenityId(amenityModel.getAmenityId());
        amenityDto.setAmenityName(amenityModel.getAmenityName());
        return amenityDto;
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
                .setAmenityName(amenityDto.getAmenityName());
    }
}
