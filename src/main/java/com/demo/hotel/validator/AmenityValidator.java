package com.demo.hotel.validator;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import static com.demo.hotel.validator.HotelValidator.EMPTY_FIELD;
import static com.demo.hotel.validator.HotelValidator.INVALID_LENGTH_FIELD;

public class AmenityValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmenityValidator.class);


    public static final String AMENITY_NAME_FIELD = "amenityName";


    public void validate(AmenityDto amenityDto) throws InvalidHotelFieldException {
        LOGGER.info("Validating amenityDto");
        if(ObjectUtils.isEmpty(amenityDto.getAmenityName())){
            throw new InvalidHotelFieldException(String.format(EMPTY_FIELD, AMENITY_NAME_FIELD));
        }
        if(amenityDto.getAmenityName().length() > 50){
            throw new InvalidHotelFieldException(String.format(INVALID_LENGTH_FIELD, AMENITY_NAME_FIELD, 50));
        }
    }
}
