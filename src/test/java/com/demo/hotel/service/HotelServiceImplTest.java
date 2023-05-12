package com.demo.hotel.service;

import com.demo.hotel.dto.HotelDto;
import com.demo.hotel.dto.HotelListDto;
import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.repository.HotelRepository;
import com.demo.hotel.repository.HotelRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class HotelServiceImplTest {

    @InjectMocks
    private HotelServiceImpl sut;
    @Mock
    private HotelRepository hotelRepositoryMock;

    @Test
    void createHotel() {
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
    void updateHotel() {
        //scenario setup
        final String hotelNameUpdated = "Hotel-1-updated";
        final HotelDto hotelDto = new HotelDto()
                .setHotelId(1L)
                .setHotelName("Hotel-1")
                .setAddress("Test address")
                .setRating(5);
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
        when(hotelRepositoryMock.findById(hotelId))
                .thenReturn(Optional.of(HotelRepositoryTest.HOTEL_RECORD_1));
        // test
        boolean result = sut.deleteHotel(hotelId);
        // Verify result
        Assertions.assertTrue(result);
    }

    @Test
    void findHotelsByName() {
        // scenario setup
        String hotelName = "hotel-1";
        PageRequest pageRequest = PageRequest.of(1, 10);
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(10)
                .mapToObj(HotelRepositoryTest.HOTEL_RECORD_1::setHotelId)
                .collect(Collectors.toList());
        when(hotelRepositoryMock.findByHotelName(hotelName, pageRequest)).thenReturn(hotelList);
        // test
        List<HotelDto> result = sut.findHotelsByName(hotelName, pageRequest);
        // Verify Result
        Assertions.assertEquals(10, result.size());

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
    void getHotelList() {
        // scenario setup
        Long recordsPerPage = 10L;
        Long totalRecords = 100L;
        int totalPages = 10;
        PageRequest pageRequest = PageRequest.of(0, recordsPerPage.intValue());
        List<Hotel> hotelList = LongStream.iterate(1, n -> n + 1)
                .limit(recordsPerPage)
                .mapToObj(HotelRepositoryTest.HOTEL_RECORD_1::setHotelId)
                .collect(Collectors.toList());
        when(hotelRepositoryMock.findAll(pageRequest)).thenReturn(new PageImpl<>(hotelList, Pageable.ofSize(10), totalRecords));
        // test
        HotelListDto hotelList1 = sut.getHotelList(pageRequest);
        // Verify result
        Assertions.assertEquals(recordsPerPage, hotelList1.getHotelDtoList().size());
        Assertions.assertEquals(totalRecords, hotelList1.getTotalElements());
        Assertions.assertEquals(totalPages, hotelList1.getTotalPages());
    }

}