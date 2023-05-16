package com.demo.hotel.repository;

import com.demo.hotel.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;


@DataJpaTest
public class HotelRepositoryTest {

    private static final String HOTEL_NAME_UPDATE = "Hotel_1_update";
    public static final Hotel HOTEL_RECORD_1 = new Hotel()
            .setHotelName("Hotel_1")
            .setAddress("123 TestStreet1, Monterrey, N.L.")
            .setRating(5);

    @Autowired
    private HotelRepository hotelRepositoryTested;

    @Test
    public void test_create_hotel() {
        Hotel result = hotelRepositoryTested.save(HOTEL_RECORD_1);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getHotelId());
        Assertions.assertNotNull(result.getAddress());
        Assertions.assertEquals(5, result.getRating());
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
    public void test_delete_hotel() {
        Hotel hotel = hotelRepositoryTested.save(HOTEL_RECORD_1);
        hotelRepositoryTested.delete(hotel);
        Optional<Hotel> result = hotelRepositoryTested.findById(hotel.getHotelId());
        Assertions.assertFalse(result.isPresent());
    }


    @Test
    public void test_get_hotel_list_by_hotel_name() {
        // scenario setup
        final String hotelName = "%Hotel%";
        final long expectedRecords = 10L;
        final int pageSize = 5;
        final PageRequest pageRequest = PageRequest.of(0, pageSize);
        // we will retrieve the preloaded records from the script /src/test/resources/data.sql
        Page<Hotel> result = hotelRepositoryTested.findByHotelNameLikeIgnoreCase(hotelName, pageRequest);
        // Verify result
        Assertions.assertEquals(expectedRecords, result.getTotalElements());
        Assertions.assertEquals(pageSize, result.getContent().size());
    }


    @Test
    public void test_get_hotel_list() {
        // scenario setup
        final long expectedRecords = 10L;
        final int pageSize = 5;
        final PageRequest pageRequest = PageRequest.of(0, pageSize);
        // Test
        // we will retrieve the preloaded records from the script /src/test/resources/data.sql
        Page<Hotel> result = hotelRepositoryTested.findAll(pageRequest);
        // Verify result
        Assertions.assertEquals(expectedRecords, result.getTotalElements());
        Assertions.assertEquals(pageSize, result.getContent().size());
    }
}