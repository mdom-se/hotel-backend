package com.demo.hotel.service;

import com.demo.hotel.mapper.AmenityMapper;
import com.demo.hotel.model.Amenity;
import com.demo.hotel.repository.AmenityRepository;
import com.demo.hotel.repository.HotelRepository;
import com.demo.hotel.webservice.dto.AmenityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmenityServiceImplTest {

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private AmenityServiceImpl sut;


    @Test
    void createAmenity() {
        // scenario setup
        final Long amenityId = 10L;
        final AmenityDto amenityDto = new AmenityDto();
        amenityDto.setHotelId(1L);
        amenityDto.setAmenityName("Spa");
        when(hotelRepository.existsById(amenityDto.getHotelId())).thenReturn(true);
        when(amenityRepository.save(argThat(amenity -> amenity.getAmenityName().equals(AmenityMapper.mapToModel(amenityDto).getAmenityName()))))
                .thenAnswer(input -> AmenityMapper.mapToModel(amenityDto).setAmenityId(amenityId));
        //Test
        AmenityDto result = sut.createAmenity(amenityDto);
        // Verify result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(amenityId, result.getAmenityId());
    }

    @Test
    void createAmenity_invalidHotelId() {
        // scenario setup
        final long hotelId = -1L;
        final AmenityDto amenityDto = new AmenityDto();
        amenityDto.setHotelId(hotelId);
        amenityDto.setAmenityName("Spa");
        // Test
        AmenityDto result = sut.createAmenity(amenityDto);
        // Verify result
        Assertions.assertNull(result);
    }

    @Test
    void deleteAmenity() {
        // scenario setup
        final Long amenityId = 10L;
        when(amenityRepository.existsById(amenityId)).thenReturn(true);
        doNothing().when(amenityRepository).deleteById(amenityId);
        // test
        boolean result = sut.deleteAmenity(amenityId);
        // Verify Result
        verify(amenityRepository, times(1)).deleteById(amenityId);
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAmenity_does_not_exists() {
        // scenario setup
        final Long amenityId = 10L;
        when(amenityRepository.existsById(amenityId)).thenReturn(false);
        // test
        boolean result = sut.deleteAmenity(amenityId);
        // Verify Result
        verify(amenityRepository, times(0)).deleteById(amenityId);
        Assertions.assertFalse(result);
    }

    @Test
    void findAmenitiesByHotelId() {
        // scenario setup
        final long hotelId = 5L;
        final int listSize = 5;
        when(amenityRepository.findAmenitiesByHotelId(hotelId)).thenReturn(
                LongStream.iterate(1, i -> i + 1)
                        .limit(listSize).
                        mapToObj(i -> new Amenity().setAmenityId(i))
                        .collect(Collectors.toList()) // return random list
        );
        // Test
        List<AmenityDto> result = sut.findAmenitiesByHotelId(hotelId);
        // Verify result
        Assertions.assertEquals(listSize, result.size());
        Assertions.assertInstanceOf(AmenityDto.class, result.get(0));
    }
}