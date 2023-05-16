package com.demo.hotel.validator;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.webservice.dto.HotelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class HotelValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelValidator.class);

    public static final String EMPTY_FIELD = "%s value is empty";
    public static final String INVALID_LENGTH_FIELD = "%s length is invalid, must not be greater than %d";
    public static final String INVALID_RANGE_VALUE_FIELD = "%s value must be a value between %d and %d";
    public static final String HOTEL_NAME_FIELD = "hotelName";
    public static final String ADDRESS_FIELD = "address";
    public static final String RATING_FIELD = "rating";


    public void validate(HotelDto hotelDto) throws InvalidHotelFieldException {
        LOGGER.info("Validating hotelDto");
        if(ObjectUtils.isEmpty(hotelDto.getHotelName())){
            throw new InvalidHotelFieldException(String.format(EMPTY_FIELD, HOTEL_NAME_FIELD));
        }
        if(hotelDto.getHotelName().length() > 50){
            throw new InvalidHotelFieldException(String.format(INVALID_LENGTH_FIELD, HOTEL_NAME_FIELD, 50));
        }
        if(ObjectUtils.isEmpty(hotelDto.getAddress())){
            throw new InvalidHotelFieldException(String.format(EMPTY_FIELD, ADDRESS_FIELD));
        }
        if(hotelDto.getAddress().length() > 100){
            throw new InvalidHotelFieldException(String.format(INVALID_LENGTH_FIELD, ADDRESS_FIELD, 100));
        }
        if(hotelDto.getRating() < 0 || hotelDto.getRating() > 5){
            throw new InvalidHotelFieldException(String.format(INVALID_RANGE_VALUE_FIELD, RATING_FIELD, 0, 5));
        }
    }
}
