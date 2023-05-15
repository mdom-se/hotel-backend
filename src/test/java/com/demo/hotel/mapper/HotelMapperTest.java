package com.demo.hotel.mapper;

import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HotelMapperTest {

    @Test
    public void mapToDto(){
        Hotel hotelModel = new Hotel();
        hotelModel.setHotelId(1L);
        hotelModel.setHotelName("Hotel-1");
        hotelModel.setAddress("Test address");
        hotelModel.setRating(5);
        HotelDto hotelDto = HotelMapper.mapToDto(hotelModel);
        Assertions.assertEquals(hotelModel.getHotelId(), hotelDto.getHotelId());
        Assertions.assertEquals(hotelModel.getHotelName(), hotelDto.getHotelName());
        Assertions.assertEquals(hotelModel.getAddress(), hotelDto.getAddress());
        Assertions.assertEquals(hotelModel.getRating(), hotelDto.getRating());
    }

    @Test
    public void mapToModel(){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(1L);
        hotelDto.setHotelName("Hotel-1");
        hotelDto.setAddress("Test address");
        hotelDto.setRating(5);
        Hotel hotelModel = HotelMapper.mapToModel(hotelDto);
        Assertions.assertEquals(hotelDto.getHotelId(), hotelModel.getHotelId());
        Assertions.assertEquals(hotelDto.getHotelName(), hotelModel.getHotelName());
        Assertions.assertEquals(hotelDto.getAddress(), hotelModel.getAddress());
        Assertions.assertEquals(hotelDto.getRating(), hotelModel.getRating());
    }

    @Test
    public void test_mapToDto_null(){
        Assertions.assertNull(HotelMapper.mapToDto(null));
    }

    @Test
    public void test_mapToModel_null(){
        Assertions.assertNull(HotelMapper.mapToModel(null));
    }
}