package com.demo.hotel.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    @Column(length = 50, nullable = false, unique = true)
    private String hotelName;
    @Column(length = 100, nullable = false)
    private String address;
    @Column(length = 1, nullable = false)
    private int rating;

    public Long getHotelId() {
        return hotelId;
    }

    public Hotel setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Hotel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Hotel setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Hotel setRating(int rating) {
        this.rating = rating;
        return this;
    }

}
