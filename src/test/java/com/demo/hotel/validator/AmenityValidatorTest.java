package com.demo.hotel.validator;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.demo.hotel.validator.AmenityValidator.AMENITY_NAME_FIELD;
import static com.demo.hotel.validator.HotelValidator.EMPTY_FIELD;
import static com.demo.hotel.validator.HotelValidator.INVALID_LENGTH_FIELD;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmenityValidatorTest {

    private final AmenityValidator amenityValidator = new AmenityValidator();

    @Test
    void test_validate_amenityDto() {
        AmenityDto amenityDto = new AmenityDto();
        amenityDto.setAmenityName("Amenity Example");
        assertDoesNotThrow(() -> amenityValidator.validate(amenityDto));
    }

    @Test
    void test_validate_empty_amenityName() {
        AmenityDto amenityDto = new AmenityDto();
        amenityDto.setAmenityName("");
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> amenityValidator.validate(amenityDto));
        Assertions.assertEquals(String.format(EMPTY_FIELD, AMENITY_NAME_FIELD), exception.getMessage());
    }

    @Test
    void test_validate_invalidLength_amenityName() {
        AmenityDto amenityDto = new AmenityDto();
        amenityDto.setAmenityName(IntStream.iterate(1, n -> n+1)
                .limit(51)
                .mapToObj(i -> "a")
                .collect(Collectors.joining()));
        InvalidHotelFieldException exception = assertThrows(InvalidHotelFieldException.class,
                () -> amenityValidator.validate(amenityDto));
        Assertions.assertEquals(String.format(INVALID_LENGTH_FIELD, AMENITY_NAME_FIELD, 50), exception.getMessage());

    }
}