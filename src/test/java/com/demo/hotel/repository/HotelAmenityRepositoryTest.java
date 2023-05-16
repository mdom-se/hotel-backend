package com.demo.hotel.repository;

import com.demo.hotel.model.Hotel;
import com.demo.hotel.model.HotelAmenity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
class HotelAmenityRepositoryTest {

    @Autowired
    private HotelAmenityRepository hotelAmenityRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Test
    void findAmenitiesByHotelId() {
        // scenario setup
        // We are using a preloaded record from data.sql
        Optional<Hotel> hotel = hotelRepository.findByHotelName("Hotel Rivera Maya");
        // test
        List<HotelAmenity> result = hotelAmenityRepository.findHotelsAmenitiesByHotelId(hotel.orElse(new Hotel()).getHotelId());
        // Verify result
        Assertions.assertEquals(5, result.size());
        Assertions.assertInstanceOf(HotelAmenity.class, result.get(0));
    }

    @Test
    void deleteByHotelId() {
        // scenario setup
        // We create a new hotel
        Hotel newHotel = createHotel("Test-deleteByHotelId");
        createHotelAmenities(newHotel);
        // Test
        long result = hotelAmenityRepository.deleteByHotelId(newHotel.getHotelId());
        // Verify result
        Assertions.assertTrue(result > 0);
    }

    @Test
    void createHotelAmenity(){
        // scenario setup
        // We create a new hotel
        Hotel newHotel = createHotel("Test-createHotelAmenity");
        List<HotelAmenity> hotelAmenities = createHotelAmenities(newHotel);
        // Test
        List<HotelAmenity> hotelAmenityList = hotelAmenityRepository.saveAll(hotelAmenities);
        // Verify result
        Assertions.assertFalse(hotelAmenityList.isEmpty());
    }


    private Hotel createHotel(String hotelName){
        Hotel newHotel = new Hotel()
                .setHotelName(hotelName)
                .setAddress("TestAddress")
                .setRating(4);
        return hotelRepository.save(newHotel);
    }

    private List<HotelAmenity> createHotelAmenities(Hotel hotel){
        return amenityRepository.findAll().stream().map(amenity ->
                hotelAmenityRepository.save(new HotelAmenity()
                        .setAmenityId(amenity.getAmenityId())
                        .setHotelId(hotel.getHotelId()))
        ).collect(Collectors.toList());
    }
}