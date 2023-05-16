package com.demo.hotel.validator;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.webservice.dto.HotelDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.demo.hotel.validator.HotelValidator.ADDRESS_FIELD;
import static com.demo.hotel.validator.HotelValidator.EMPTY_FIELD;
import static com.demo.hotel.validator.HotelValidator.HOTEL_NAME_FIELD;
import static com.demo.hotel.validator.HotelValidator.INVALID_LENGTH_FIELD;
import static com.demo.hotel.validator.HotelValidator.INVALID_RANGE_VALUE_FIELD;
import static com.demo.hotel.validator.HotelValidator.RATING_FIELD;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HotelValidatorTest {

    private final HotelValidator hotelValidator = new HotelValidator();

    @Test
    void test_validate_hotelDto() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Example");
        hotelDto.setAddress("Test Address");
        hotelDto.setRating(4);
        assertDoesNotThrow(() -> hotelValidator.validate(hotelDto));
    }

    @Test
    void test_validate_empty_hotelName() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("");
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(EMPTY_FIELD, HOTEL_NAME_FIELD), exception.getMessage());
    }

    @Test
    void test_validate_invalidLength_hotelName() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName(IntStream.iterate(1, n -> n+1)
                .limit(51)
                .mapToObj(i -> "a")
                .collect(Collectors.joining()));
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(INVALID_LENGTH_FIELD, HOTEL_NAME_FIELD, 50), exception.getMessage());

    }

    @Test
    void test_validate_empty_address() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Example");
        hotelDto.setAddress("");
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(EMPTY_FIELD, ADDRESS_FIELD), exception.getMessage());

    }

    @Test
    void test_validate_invalidLength_address() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Example");
        hotelDto.setAddress(IntStream.iterate(1, n -> n+1)
                .limit(101)
                .mapToObj(i -> "a")
                .collect(Collectors.joining()));
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(INVALID_LENGTH_FIELD, ADDRESS_FIELD, 100), exception.getMessage());
    }

    @Test
    void test_validate_rating_greater_than_5() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Example");
        hotelDto.setAddress("Test Address");
        hotelDto.setRating(6);
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(INVALID_RANGE_VALUE_FIELD, RATING_FIELD, 0, 5), exception.getMessage());
    }

    @Test
    void test_validate_rating_less_than_0() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Example");
        hotelDto.setAddress("Test Address");
        hotelDto.setRating(-1);
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> hotelValidator.validate(hotelDto));
        Assertions.assertEquals(String.format(INVALID_RANGE_VALUE_FIELD, RATING_FIELD, 0, 5), exception.getMessage());
    }
}