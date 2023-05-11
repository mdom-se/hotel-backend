package com.demo.hotel.dto;

public class HotelDto {
    private Long hotelId;
    private String hotelName;
    private String address;
    private Integer rating;

    public Long getHotelId() {
        return hotelId;
    }

    public HotelDto setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public HotelDto setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public HotelDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public HotelDto setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
