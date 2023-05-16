package com.demo.hotel.exception;

public class InvalidHotelFieldException extends RuntimeException {

    public InvalidHotelFieldException(String message){
        super(message);
    }

}
