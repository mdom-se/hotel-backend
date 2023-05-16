package com.demo.hotel.mapper;

import com.demo.hotel.model.Amenity;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmenityMapperTest {

    @Test
    public void mapToDto(){
        Amenity amenityModel = new Amenity();
        amenityModel.setAmenityName("Swimming Pool");
        amenityModel.setAmenityId(1L);
        AmenityDto amenityDto = AmenityMapper.mapToDto(amenityModel);
        Assertions.assertEquals(amenityModel.getAmenityId(), amenityDto.getAmenityId());
        Assertions.assertEquals(amenityModel.getAmenityName(), amenityDto.getAmenityName());
    }

    @Test
    public void mapToModel(){
        AmenityDto amenityDto = new AmenityDto();
        amenityDto.setAmenityName("Swimming Pool");
        amenityDto.setAmenityId(1L);
        Amenity amenityModel = AmenityMapper.mapToModel(amenityDto);
        Assertions.assertEquals(amenityDto.getAmenityId(), amenityModel.getAmenityId());
        Assertions.assertEquals(amenityDto.getAmenityName(), amenityModel.getAmenityName());
    }

    @Test
    public void test_mapToDto_null(){
        Assertions.assertNull(AmenityMapper.mapToDto(null));
    }

    @Test
    public void test_mapToModel_null(){
        Assertions.assertNull(AmenityMapper.mapToModel(null));
    }
}