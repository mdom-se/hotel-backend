package com.demo.hotel.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "hotels_amenities")
public class HotelAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelAmenityId;

    @Column(nullable = false)
    private Long hotelId;

    @Column(nullable = false)
    private Long amenityId;

    public Long getHotelAmenityId() {
        return hotelAmenityId;
    }

    public HotelAmenity setHotelAmenityId(Long hotelAmenityId) {
        this.hotelAmenityId = hotelAmenityId;
        return this;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public HotelAmenity setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Long getAmenityId() {
        return amenityId;
    }

    public HotelAmenity setAmenityId(Long amenityId) {
        this.amenityId = amenityId;
        return this;
    }
}
