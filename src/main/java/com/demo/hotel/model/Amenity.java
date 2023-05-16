package com.demo.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;
    @Column(length = 50, nullable = false)
    private String amenityName;

    public Long getAmenityId() {
        return amenityId;
    }

    public Amenity setAmenityId(Long amenityId) {
        this.amenityId = amenityId;
        return this;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public Amenity setAmenityName(String amenityName) {
        this.amenityName = amenityName;
        return this;
    }

}
