package com.demo.hotel.mapper;

import com.demo.hotel.model.Hotel;
import com.demo.hotel.webservice.dto.HotelDto;

public class HotelMapper {

    private HotelMapper(){
        // Utility class
    }

    public static HotelDto mapToDto(Hotel hotelModel){
        if(hotelModel == null){
            return null;
        }
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(hotelModel.getHotelId());
        hotelDto.setHotelName(hotelModel.getHotelName());
        hotelDto.setAddress(hotelModel.getAddress());
        hotelDto.setRating(hotelModel.getRating());
        return hotelDto;
    }

    public static Hotel mapToModel(HotelDto hotelDto){
        if(hotelDto == null){
            return null;
        }
        return new Hotel()
                .setHotelId(hotelDto.getHotelId())
                .setHotelName(hotelDto.getHotelName())
                .setAddress(hotelDto.getAddress())
                .setRating(hotelDto.getRating());
    }
}
