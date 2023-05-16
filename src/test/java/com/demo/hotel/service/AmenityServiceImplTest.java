package com.demo.hotel.service;

import com.demo.hotel.model.Amenity;
import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.repository.AmenityRepository;
import com.demo.hotel.repository.HotelAmenityRepository;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmenityServiceImplTest {

    @Mock
    private HotelAmenityRepository hotelAmenityRepository;

    @Mock
    private AmenityRepository amenityRepository;

    @InjectMocks
    private AmenityServiceImpl sut;

    @Test
    void findAmenitiesByHotelId() {
        // scenario setup
        final long hotelId = 5L;
        final int listSize = 5;
        // return random lists
        List<HotelAmenity> hotelAmenitiesList = LongStream.iterate(1, i -> i + 1)
                .limit(listSize)
                .mapToObj(i -> new HotelAmenity()
                        .setHotelAmenityId(i)
                        .setAmenityId(i))
                .collect(Collectors.toList());
        List<Amenity> amenityList = hotelAmenitiesList.stream()
                .map(hotelAmenity -> new Amenity().setAmenityId(hotelAmenity.getAmenityId()))
                .collect(Collectors.toList());

        when(hotelAmenityRepository.findHotelsAmenitiesByHotelId(hotelId))
                .thenReturn(hotelAmenitiesList);
        when(amenityRepository.findAllById(anyCollection()))
                .thenReturn(amenityList);// return random list
        // Test
        List<AmenityDto> result = sut.findAmenitiesByHotelId(hotelId);
        // Verify result
        Assertions.assertEquals(listSize, result.size());
        Assertions.assertInstanceOf(AmenityDto.class, result.get(0));
    }

    @Test
    void findAmenitiesByHotelId_not_found_records() {
        // scenario setup
        final long hotelId = 5L;
        final int listSize = 0;
        // return random lists
        List<HotelAmenity> hotelAmenitiesList = Collections.emptyList();

        when(hotelAmenityRepository.findHotelsAmenitiesByHotelId(hotelId))
                .thenReturn(hotelAmenitiesList);

        // Test
        List<AmenityDto> result = sut.findAmenitiesByHotelId(hotelId);
        // Verify result
        Assertions.assertEquals(listSize, result.size());
    }

}