package com.demo.hotel.dto;

import java.util.List;

public class HotelListDto {

    /**
     * Hotel List Dtos
     */
    List<HotelDto> hotelDtoList;

    /**
     * Total amount of elements.
     */
    private long totalElements;

    /**
     * The number of total pages.
     */
    private int totalPages;

    public List<HotelDto> getHotelDtoList() {
        return hotelDtoList;
    }

    public HotelListDto setHotelDtoList(List<HotelDto> hotelDtoList) {
        this.hotelDtoList = hotelDtoList;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public HotelListDto setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public HotelListDto setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
