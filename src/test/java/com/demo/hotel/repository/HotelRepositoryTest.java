package com.demo.hotel.repository;

import com.demo.hotel.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@DataJpaTest
public class HotelRepositoryTest {

    private static final String HOTEL_NAME_UPDATE = "Hotel_1_update";
    public static final Hotel HOTEL_RECORD_1 = new Hotel()
            .setHotelName("Hotel_1")
            .setAddress("123 TestStreet1, Monterrey, N.L.")
            .setRating(5);

    public static final Hotel HOTEL_RECORD_2 = new Hotel()
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
    public void test_find_hotel_by_name() {
        Hotel hotel1 = hotelRepositoryTested.save(HOTEL_RECORD_1);
        hotelRepositoryTested.save(HOTEL_RECORD_2);
        List<Hotel> result = hotelRepositoryTested.findByHotelName(hotel1.getHotelName(), Pageable.ofSize(10));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void test_delete_hotel() {
        Hotel hotel = hotelRepositoryTested.save(HOTEL_RECORD_1);
        hotelRepositoryTested.delete(hotel);
        Optional<Hotel> result = hotelRepositoryTested.findById(hotel.getHotelId());
        Assertions.assertFalse(result.isPresent());
    }


    @Test
    public void test_get_hotel_list() {
        // scenario setup
        Long totalRecords = 100L;
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(totalRecords) // 100 records
                .mapToObj(n -> new Hotel()
                        .setHotelName("Hotel-" + n)
                        .setRating(5)
                        .setAddress("Test address")
                ).collect(Collectors.toList());
        hotelRepositoryTested.saveAll(hotelList);
        // Test
        Page<Hotel> result = hotelRepositoryTested.findAll(pageRequest);
        // Verify result
        Assertions.assertEquals(totalRecords, result.getTotalElements());
        Assertions.assertEquals(10, result.getContent().size());
    }
}