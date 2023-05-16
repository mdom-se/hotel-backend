package com.demo.hotel.webservice;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.service.AmenityService;
import com.demo.hotel.service.HotelAmenityService;
import com.demo.hotel.service.HotelService;
import com.demo.hotel.webservice.dto.AddHotelAmenityRequest;
import com.demo.hotel.webservice.dto.AddHotelAmenityResponse;
import com.demo.hotel.webservice.dto.AddHotelRequest;
import com.demo.hotel.webservice.dto.AddHotelResponse;
import com.demo.hotel.webservice.dto.AmenityDto;
import com.demo.hotel.webservice.dto.DeleteHotelAmenityRequest;
import com.demo.hotel.webservice.dto.DeleteHotelAmenityResponse;
import com.demo.hotel.webservice.dto.DeleteHotelRequest;
import com.demo.hotel.webservice.dto.DeleteHotelResponse;
import com.demo.hotel.webservice.dto.GetAmenityListRequest;
import com.demo.hotel.webservice.dto.GetAmenityListResponse;
import com.demo.hotel.webservice.dto.GetHotelListRequest;
import com.demo.hotel.webservice.dto.GetHotelListResponse;
import com.demo.hotel.webservice.dto.GetHotelRequest;
import com.demo.hotel.webservice.dto.GetHotelResponse;
import com.demo.hotel.webservice.dto.HotelAmenityDto;
import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import com.demo.hotel.webservice.dto.UpdateHotelRequest;
import com.demo.hotel.webservice.dto.UpdateHotelResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class HotelWebServiceEndpointTest {

    @InjectMocks
    private HotelWebServiceEndpoint wsEndpoint;

    @Mock
    private HotelService hotelService;

    @Mock
    private HotelAmenityService hotelAmenityService;

    @Mock
    private AmenityService amenityService;

    private HotelDto newHotelDto() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Rivera Maya");
        hotelDto.setAddress("Test address");
        hotelDto.setRating(5);
        return hotelDto;
    }

    private List<HotelDto> createHotelList() {
        return LongStream.iterate(1, i -> i + 1)
                .limit(5)
                .mapToObj(id -> {
                    HotelDto dto = newHotelDto();
                    dto.setHotelId(id);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Test
    void getHotel() {
        // scenario setup
        Long hotelId = 1L;
        GetHotelRequest request = new GetHotelRequest();
        request.setHotelId(hotelId);
        when(hotelService.findHotelById(hotelId))
                .thenReturn(HotelMapper.mapToDto(new Hotel().setHotelId(hotelId)));
        // test
        GetHotelResponse response = wsEndpoint.getHotel(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertEquals(hotelId, response.getHotelDto().getHotelId());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void getHotel_notFound() {
        // scenario setup
        long hotelId = 1L;
        GetHotelRequest request = new GetHotelRequest();
        request.setHotelId(hotelId);
        when(hotelService.findHotelById(hotelId))
                .thenReturn(null);
        // test
        GetHotelResponse response = wsEndpoint.getHotel(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertNull(response.getHotelDto());
        Assertions.assertEquals(NOT_FOUND.value(), response.getStatusCode());
        Assertions.assertEquals(NOT_FOUND.name(), response.getMessage());
    }


    @Test
    void addHotel() throws InvalidHotelFieldException {
        // scenario setup
        Long hotelId = 10L;
        HotelDto hotelDto = newHotelDto();
        AddHotelRequest request = new AddHotelRequest();
        request.setHotelDto(hotelDto);
        when(hotelService.createHotel(hotelDto))
                .thenAnswer(inArgs -> {
                    HotelDto dto = inArgs.getArgument(0, HotelDto.class);
                    dto.setHotelId(hotelId);
                    return hotelDto;
                });
        // test
        AddHotelResponse response = wsEndpoint.addHotel(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertEquals(hotelId, response.getHotelDto().getHotelId());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }


    @Test
    void updateHotel() throws InvalidHotelFieldException {
        // scenario setup
        Long hotelId = 10L;
        HotelDto hotelDto = newHotelDto();
        hotelDto.setHotelId(hotelId);
        UpdateHotelRequest request = new UpdateHotelRequest();
        request.setHotelDto(hotelDto);
        when(hotelService.updateHotel(hotelDto))
                .thenReturn(hotelDto);
        // test
        UpdateHotelResponse response = wsEndpoint.updateHotel(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertEquals(hotelId, response.getHotelDto().getHotelId());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void deleteHotel() {
        // scenario setup
        DeleteHotelRequest request = new DeleteHotelRequest();
        request.setHotelId(1L);
        when(hotelService.deleteHotel(request.getHotelId())).thenReturn(true);
        // test
        DeleteHotelResponse response = wsEndpoint.deleteHotel(request);
        // Verify result
        Assertions.assertTrue(response.isResult());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void getHotelList() {
        // scenario setup
        GetHotelListRequest request = new GetHotelListRequest();
        request.setPage(0);
        request.setPageSize(5);
        request.setHotelName("Hotel Rivera");
        when(hotelService.getHotelList(eq(request.getHotelName()), any(Pageable.class)))
                .thenAnswer(
                        input -> {
                            HotelListDto dto = new HotelListDto();
                            dto.getHotelDtoList().addAll(createHotelList());
                            dto.setTotalElements(5);
                            dto.setTotalPages(1);
                            return dto;
                        });

        // test
        GetHotelListResponse response = wsEndpoint.getHotelList(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getHotelListDto());
        Assertions.assertEquals(5, response.getHotelListDto().getTotalElements());
        Assertions.assertEquals(1, response.getHotelListDto().getTotalPages());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void addHotelAmenity() {
        // scenario setup
        Long hotelAmenityId = 20L;
        AddHotelAmenityRequest request = new AddHotelAmenityRequest();
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setAmenityId(3L);
        hotelAmenityDto.setHotelId(4L);
        request.setHotelAmenityDto(hotelAmenityDto);
        when(hotelAmenityService.addAmenityToHotel(hotelAmenityDto))
                .thenAnswer(invocationOnMock -> {
                    HotelAmenityDto result = invocationOnMock.getArgument(0, HotelAmenityDto.class);
                    result.setHotelAmenityId(hotelAmenityId);
                    return result;
                });
        // test
        AddHotelAmenityResponse response = wsEndpoint.addHotelAmenity(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertEquals(hotelAmenityId, response.getHotelAmenityDto().getHotelAmenityId());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void deleteHotelAmenity() {
        // scenario setup
        HotelAmenityDto hotelAmenityDto = new HotelAmenityDto();
        hotelAmenityDto.setAmenityId(3L);
        hotelAmenityDto.setHotelId(4L);
        hotelAmenityDto.setHotelAmenityId(3L);
        DeleteHotelAmenityRequest request = new DeleteHotelAmenityRequest();
        request.setHotelAmenityDto(hotelAmenityDto);
        when(hotelAmenityService.deleteAmenityFromHotel(hotelAmenityDto)).thenReturn(true);
        // test
        DeleteHotelAmenityResponse response = wsEndpoint.deleteHotelAmenity(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isResult());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }

    @Test
    void getAmenityList() {
        // scenario setup
        GetAmenityListRequest request = new GetAmenityListRequest();
        request.setHotelId(1L);
        when(amenityService.findAmenitiesByHotelId(request.getHotelId()))
                .thenReturn(Collections.singletonList(new AmenityDto()));
        // test
        GetAmenityListResponse response = wsEndpoint.getAmenityList(request);
        // verify result
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getAmenityListDto().size());
        Assertions.assertEquals(OK.value(), response.getStatusCode());
        Assertions.assertEquals(OK.name(), response.getMessage());
    }
}