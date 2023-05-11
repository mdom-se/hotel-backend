package com.demo.hotel.repository;

import com.demo.hotel.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class HotelRepositoryTest {

    private static final String HOTEL_NAME_UPDATE = "Hotel_1_update";
    private static final Hotel HOTEL_RECORD_1 = new Hotel()
            .setHotelName("Hotel_1")
            .setAddress("123 TestStreet1, Monterrey, N.L.")
            .setRating(5);

    private static final Hotel HOTEL_RECORD_2 = new Hotel()
            .setHotelName("Hotel_2")
            .setAddress("123 TestStreet2, Monterrey, N.L.")
            .setRating(5);

    @Autowired
    private HotelRepository hotelRepositoryTested;

    @Test
    public void test_create_hotel() {
        Hotel result = hotelRepositoryTested.save(HOTEL_RECORD_1);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHotelId());
        Assertions.assertNotNull(result.getAddress());
        Assertions.assertEquals(5 ,result.getRating());
    }

    @Test
    public void test_update_hotel() {
        Hotel originalHotel = hotelRepositoryTested.save(HOTEL_RECORD_1);
        originalHotel.setHotelName(HOTEL_NAME_UPDATE);
        Hotel result = hotelRepositoryTested.save(originalHotel);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(originalHotel.getHotelId(), result.getHotelId());
        Assertions.assertEquals(HOTEL_NAME_UPDATE, result.getHotelName());
    }

    @Test
    public void test_find_hotel_by_name() {
        Hotel hotel1 = hotelRepositoryTested.save(HOTEL_RECORD_1);
        hotelRepositoryTested.save(HOTEL_RECORD_2);
        List<Hotel> result = hotelRepositoryTested.findByHotelName(hotel1.getHotelName());
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void test_delete_hotel() {
        Hotel hotel = hotelRepositoryTested.save(HOTEL_RECORD_1);
        hotelRepositoryTested.delete(hotel);
        Optional<Hotel> result = hotelRepositoryTested.findById(hotel.getHotelId());
        Assertions.assertFalse(result.isPresent());
    }
}