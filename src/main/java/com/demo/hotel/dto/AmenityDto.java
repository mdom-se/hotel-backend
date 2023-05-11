package com.demo.hotel.dto;

public class AmenityDto {

    private Long amenityId;
    private String amenityName;
    private Long hotelId;

    public Long getAmenityId() {
        return amenityId;
    }

    public AmenityDto setAmenityId(Long amenityId) {
        this.amenityId = amenityId;
        return this;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public AmenityDto setAmenityName(String amenityName) {
        this.amenityName = amenityName;
        return this;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public AmenityDto setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }
}
