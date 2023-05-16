package com.demo.hotel.repository;

import com.demo.hotel.model.Amenity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class AmenityRepositoryTest {

    @Autowired
    private AmenityRepository sut;

    @Test
    public void test_create_amenity(){
        Amenity amenity = new Amenity()
                .setAmenityName("Swimming pool");

        Amenity result = sut.save(amenity);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getAmenityId());
        Assertions.assertNotNull(result.getAmenityName());
    }

    @Test
    public void test_delete_amenity(){
        Amenity amenity = sut.save(new Amenity()
                .setAmenityName("Swimming pool"));
        sut.delete(amenity);
        Optional<Amenity> result = sut.findById(amenity.getAmenityId());
        Assertions.assertFalse(result.isPresent());
    }

}