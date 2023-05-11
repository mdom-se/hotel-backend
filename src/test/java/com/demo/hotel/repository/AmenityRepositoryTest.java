package com.demo.hotel.repository;

import com.demo.hotel.model.Amenity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AmenityRepositoryTest {

    @Autowired
    private AmenityRepository sut;

    @Test
    public void test_create_amenity(){
        Long hotelId = 1L;
        Amenity amenity = new Amenity()
                .setAmenityName("Swimming pool")
                .setHotelId(hotelId);

        Amenity result = sut.save(amenity);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getAmenityId());
        Assertions.assertNotNull(result.getAmenityName());
        Assertions.assertNotNull(result.getHotelId());
        Assertions.assertEquals(hotelId, result.getHotelId());
    }

    @Test
    public void test_delete_amenity(){
        Long hotelId = 1L;
        Amenity amenity = sut.save(new Amenity()
                .setAmenityName("Swimming pool")
                .setHotelId(hotelId));
        sut.delete(amenity);
        Optional<Amenity> result = sut.findById(amenity.getAmenityId());
        Assertions.assertFalse(result.isPresent());
    }


    @Test
    public void test_find_amenities_by_hotel_id(){
        Long hotelId = 1L;
        Amenity amenity1 = new Amenity()
                .setAmenityName("Swimming pool")
                .setHotelId(hotelId);
        Amenity amenity2 = new Amenity()
                .setAmenityName("Bar")
                .setHotelId(hotelId);
        sut.save(amenity1);
        sut.save(amenity2);
        List<Amenity> result = sut.findAmenitiesByHotelId(hotelId);
        Assertions.assertEquals(2, result.size());
    }
}