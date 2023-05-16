package com.demo.hotel.mapper;

import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.webservice.dto.HotelAmenityDto;

public class HotelAmenityMapper {

    private HotelAmenityMapper(){
        // Utility Class
    }

    public static HotelAmenityDto mapToDto(final HotelAmenity hotelAmenityModel){
        if(hotelAmenityModel == null){
            return null;
        }
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setHotelId(hotelAmenityModel.getHotelId());
        hotelAmenityDto.setAmenityId(hotelAmenityModel.getAmenityId());
        hotelAmenityDto.setHotelAmenityId(hotelAmenityModel.getHotelAmenityId());
        return hotelAmenityDto;
    }

    /**
     *
     * @param hotelAmenityDto HotelAmenity DTO
     * @return HotelAmenity Model
     */
    public static HotelAmenity mapToModel(final HotelAmenityDto hotelAmenityDto){
        if(hotelAmenityDto == null){
            return null;
        }
        return new HotelAmenity()
                .setHotelId(hotelAmenityDto.getHotelId())
                .setAmenityId(hotelAmenityDto.getAmenityId())
                .setHotelAmenityId(hotelAmenityDto.getHotelAmenityId());
    }
}
