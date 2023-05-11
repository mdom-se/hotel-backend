package com.demo.hotel.mapper;

import com.demo.hotel.dto.HotelDto;
import com.demo.hotel.model.Hotel;

public class HotelMapper {

    private HotelMapper(){
        // Utility class
    }

    public static HotelDto mapToDto(Hotel hotelModel){
        return new HotelDto()
                .setHotelId(hotelModel.getHotelId())
                .setHotelName(hotelModel.getHotelName())
                .setAddress(hotelModel.getAddress())
                .setRating(hotelModel.getRating());
    }

    public static Hotel mapToModel(HotelDto hotelDto){
        return new Hotel()
                .setHotelId(hotelDto.getHotelId())
                .setHotelName(hotelDto.getHotelName())
                .setAddress(hotelDto.getAddress())
                .setRating(hotelDto.getRating());
    }
}
