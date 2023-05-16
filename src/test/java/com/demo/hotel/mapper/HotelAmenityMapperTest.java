package com.demo.hotel.mapper;

import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.webservice.dto.HotelAmenityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HotelAmenityMapperTest {

    @Test
    void mapToDto() {

        HotelAmenity hotelAmenity = new HotelAmenity()
                .setAmenityId(12L)
                .setHotelId(15L)
                .setHotelAmenityId(9L);

        HotelAmenityDto result = HotelAmenityMapper.mapToDto(hotelAmenity);

        Assertions.assertEquals(hotelAmenity.getHotelAmenityId(), result.getHotelAmenityId());
        Assertions.assertEquals(hotelAmenity.getAmenityId(), result.getAmenityId());
        Assertions.assertEquals(hotelAmenity.getHotelId(), result.getHotelId());

    }

    @Test
    void mapToModel() {
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setHotelAmenityId(10L);
        hotelAmenityDto.setAmenityId(12L);
        hotelAmenityDto.setHotelId(4L);

        HotelAmenity result = HotelAmenityMapper.mapToModel(hotelAmenityDto);

        Assertions.assertEquals(hotelAmenityDto.getHotelAmenityId(), result.getHotelAmenityId());
        Assertions.assertEquals(hotelAmenityDto.getAmenityId(), result.getAmenityId());
        Assertions.assertEquals(hotelAmenityDto.getHotelId(), result.getHotelId());
    }

    @Test
    void mapToDto_null() {
        Assertions.assertNull(HotelAmenityMapper.mapToDto(null));
    }

    @Test
    void mapToModel_null() {
        Assertions.assertNull(HotelAmenityMapper.mapToModel(null));
    }

}