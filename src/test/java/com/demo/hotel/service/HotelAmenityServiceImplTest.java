package com.demo.hotel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.demo.hotel.mapper.HotelAmenityMapper;
import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.repository.HotelAmenityRepository;
import com.demo.hotel.webservice.dto.HotelAmenityDto;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelAmenityServiceImplTest {

    @InjectMocks
    private HotelAmenityServiceImpl sut;

    @Mock
    private HotelAmenityRepository hotelAmenityRepository;

    @Test
    void addAmenityToHotel() {
        // scenario setup
        Long hotelAmenityId = 100L;
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setAmenityId(2L);
        hotelAmenityDto.setHotelId(1L);

        when(hotelAmenityRepository.save(any(HotelAmenity.class)))
                .thenReturn(HotelAmenityMapper.mapToModel(hotelAmenityDto)
                        .setHotelAmenityId(hotelAmenityId));
        //test
        HotelAmenityDto result = sut.addAmenityToHotel(hotelAmenityDto);

        // Verify result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(hotelAmenityId, result.getHotelAmenityId());

    }

    @Test
    void deleteAmenityFromHotel() {
        // scenario setup
        Long hotelAmenityId = 100L;
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setAmenityId(2L);
        hotelAmenityDto.setHotelId(1L);

        when(hotelAmenityRepository
                .findHotelAmenitiesByHotelIdAndAmenityId(hotelAmenityDto.getHotelId(), hotelAmenityDto.getAmenityId())
        ).thenAnswer(input -> Optional.of(HotelAmenityMapper.mapToModel(hotelAmenityDto).setHotelAmenityId(hotelAmenityId)));
        //test
        boolean result = sut.deleteAmenityFromHotel(hotelAmenityDto);

        // Verify result
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAmenityFromHotel_not_found_record() {
        // scenario setup
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setAmenityId(2L);
        hotelAmenityDto.setHotelId(1L);

        when(hotelAmenityRepository
                .findHotelAmenitiesByHotelIdAndAmenityId(hotelAmenityDto.getHotelId(), hotelAmenityDto.getAmenityId())
        ).thenAnswer(input -> Optional.empty());
        //test
        boolean result = sut.deleteAmenityFromHotel(hotelAmenityDto);

        // Verify result
        Assertions.assertFalse(result);
    }

    @Test
    void deleteHotelAmenitiesByHotelId() {
        // scenario setup
        Long hotelId = 30L;
        Long deletedRecords = 5L;
        when(hotelAmenityRepository.deleteByHotelId(hotelId)).thenReturn(deletedRecords);

        // test
        Long result = sut.deleteHotelAmenitiesByHotelId(hotelId);

        // Verify result
        Assertions.assertEquals(deletedRecords, result);
    }


}