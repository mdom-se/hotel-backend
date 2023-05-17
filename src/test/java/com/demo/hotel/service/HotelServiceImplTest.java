package com.demo.hotel.service;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.repository.HotelRepository;
import com.demo.hotel.repository.HotelRepositoryTest;
import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @InjectMocks
    private HotelServiceImpl sut;
    @Mock
    private HotelRepository hotelRepositoryMock;
    @Mock
    private HotelAmenityService hotelAmenityService;



    @Test
    void createHotel() throws InvalidHotelFieldException {
        // scenario setup
        Long hotelId = 1L;
        Hotel hotel = HotelRepositoryTest.HOTEL_RECORD_1;
        HotelDto hotelDto = HotelMapper.mapToDto(hotel);
        when(hotelRepositoryMock.save(any(Hotel.class))).thenReturn(hotel.setHotelId(hotelId));
        // Test
        HotelDto result = sut.createHotel(hotelDto);
        //Verify result
        Assertions.assertEquals(hotelId, result.getHotelId());
        Assertions.assertEquals(hotelDto.getHotelName(), result.getHotelName());
        Assertions.assertEquals(hotelDto.getAddress(), result.getAddress());
        Assertions.assertEquals(hotelDto.getRating(), result.getRating());
    }

    @Test
    void updateHotel() throws InvalidHotelFieldException {
        //scenario setup
        final String hotelNameUpdated = "Hotel-1-updated";
        final HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(1L);
        hotelDto.setHotelName("Hotel-1");
        hotelDto.setAddress("Test address");
        hotelDto.setRating(5);
        when(hotelRepositoryMock.save(any(Hotel.class)))
                .thenReturn(HotelMapper.mapToModel(hotelDto)
                        .setHotelName(hotelNameUpdated)); //return the entity updated
        //Test
        hotelDto.setHotelName(hotelNameUpdated);
        HotelDto result = sut.updateHotel(hotelDto);
        // Verify result
        Assertions.assertEquals(hotelNameUpdated, result.getHotelName());
    }

    @Test
    void deleteHotel() {
        // scenario setup
        Long hotelId = 1L;
        when(hotelRepositoryMock.existsById(hotelId)).thenReturn(true);
        when(hotelAmenityService.deleteHotelAmenitiesByHotelId(hotelId)).thenReturn(1L);
        doNothing().when(hotelRepositoryMock).deleteById(hotelId);
        // test
        boolean result = sut.deleteHotel(hotelId);
        // Verify result
        Assertions.assertTrue(result);
        verify(hotelAmenityService, times(1)).deleteHotelAmenitiesByHotelId(hotelId);
        verify(hotelRepositoryMock, times(1)).deleteById(hotelId);
    }

    @Test
    void deleteHotel_does_not_exists() {
        // scenario setup
        Long hotelId = 1L;
        when(hotelRepositoryMock.existsById(hotelId)).thenReturn(false);
        // test
        boolean result = sut.deleteHotel(hotelId);
        // Verify result
        verify(hotelRepositoryMock, times(0)).deleteById(hotelId);
        Assertions.assertFalse(result);
    }

    @Test
    void findHotelById() {
        // scenario setup
        Long hotelId = 1L;
        when(hotelRepositoryMock.findById(hotelId))
                .thenReturn(Optional.of(HotelRepositoryTest.HOTEL_RECORD_1));
        //test
        HotelDto result = sut.findHotelById(hotelId);
        //Verify result
        Assertions.assertNotNull(result);
    }

    @Test
    void getHotelList_empty_hotelName() {
        // scenario setup
        final String hotelName = "";
        final int pageSize = 10;
        final Long totalRecords = 100L;
        final int totalPages = 10;
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(pageSize)
                .mapToObj(HotelRepositoryTest.HOTEL_RECORD_1::setHotelId)
                .collect(Collectors.toList());
        when(hotelRepositoryMock.findAll(pageRequest)).thenReturn(new PageImpl<>(hotelList, pageRequest, totalRecords));
        // test
        HotelListDto hotelList1 = sut.getHotelList(hotelName, pageRequest);
        // Verify result
        Assertions.assertEquals(pageSize, hotelList1.getHotelDtoList().size());
        Assertions.assertEquals(totalRecords, hotelList1.getTotalElements());
        Assertions.assertEquals(totalPages, hotelList1.getTotalPages());
    }

    @Test
    void getHotelList_null_hotelName() {
        // scenario setup
        final int pageSize = 10;
        final Long totalRecords = 100L;
        final int totalPages = 10;
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(pageSize)
                .mapToObj(HotelRepositoryTest.HOTEL_RECORD_1::setHotelId)
                .collect(Collectors.toList());
        when(hotelRepositoryMock.findAll(pageRequest)).thenReturn(new PageImpl<>(hotelList, pageRequest, totalRecords));
        // test
        HotelListDto hotelList1 = sut.getHotelList(null, pageRequest);
        // Verify result
        Assertions.assertEquals(pageSize, hotelList1.getHotelDtoList().size());
        Assertions.assertEquals(totalRecords, hotelList1.getTotalElements());
        Assertions.assertEquals(totalPages, hotelList1.getTotalPages());
    }

    @Test
    void getHotelList_by_hotelName() {
        // scenario setup
        final String hotelName = "Hotel";
        final int pageSize = 10;
        final Long totalRecords = 100L;
        final int totalPages = 10;
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(pageSize)
                .mapToObj(HotelRepositoryTest.HOTEL_RECORD_1::setHotelId)
                .collect(Collectors.toList());
        when(hotelRepositoryMock.findByHotelNameLikeIgnoreCaseOrderByHotelNameAsc(any(String.class), eq(pageRequest))).thenReturn(new PageImpl<>(hotelList, pageRequest, totalRecords));
        // test
        HotelListDto hotelList1 = sut.getHotelList(hotelName, pageRequest);
        // Verify result
        Assertions.assertEquals(pageSize, hotelList1.getHotelDtoList().size());
        Assertions.assertEquals(totalRecords, hotelList1.getTotalElements());
        Assertions.assertEquals(totalPages, hotelList1.getTotalPages());
    }

}