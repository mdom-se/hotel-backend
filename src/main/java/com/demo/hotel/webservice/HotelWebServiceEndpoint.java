package com.demo.hotel.webservice;

import com.demo.hotel.service.AmenityService;
import com.demo.hotel.service.HotelAmenityService;
import com.demo.hotel.service.HotelService;
import com.demo.hotel.validator.HotelValidator;
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
import com.demo.hotel.webservice.dto.GetHotelAmenityListRequest;
import com.demo.hotel.webservice.dto.GetHotelAmenityListResponse;
import com.demo.hotel.webservice.dto.GetHotelListRequest;
import com.demo.hotel.webservice.dto.GetHotelListResponse;
import com.demo.hotel.webservice.dto.GetHotelRequest;
import com.demo.hotel.webservice.dto.GetHotelResponse;
import com.demo.hotel.webservice.dto.HotelAmenityDto;
import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import com.demo.hotel.webservice.dto.UpdateHotelRequest;
import com.demo.hotel.webservice.dto.UpdateHotelResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static com.demo.hotel.webservice.config.WSConfiguration.WS_TARGET_NAMESPACE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Endpoint
public class HotelWebServiceEndpoint {

    private final HotelService hotelService;

    private final AmenityService amenityService;

    private final HotelAmenityService hotelAmenityService;


    public HotelWebServiceEndpoint(final HotelService hotelService,
                                   final AmenityService amenityService,
                                   final HotelAmenityService hotelAmenityService) {
        this.hotelService = hotelService;
        this.amenityService = amenityService;
        this.hotelAmenityService = hotelAmenityService;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getHotelRequest")
    @ResponsePayload
    public GetHotelResponse getHotel(@RequestPayload GetHotelRequest getHotelRequest) {
        GetHotelResponse response = new GetHotelResponse();
        HotelDto hotelDto = hotelService.findHotelById(getHotelRequest.getHotelId());
        response.setHotelDto(hotelDto);
        HttpStatus status = hotelDto != null ? OK : NOT_FOUND;
        response.setStatusCode(status.value());
        response.setMessage(status.name());
        return response;
    }


    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "addHotelRequest")
    @ResponsePayload
    public AddHotelResponse addHotel(@RequestPayload AddHotelRequest addHotelRequest) {
        AddHotelResponse response = new AddHotelResponse();
        HotelDto hotelDto = hotelService.createHotel(addHotelRequest.getHotelDto());
        response.setHotelDto(hotelDto);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "updateHotelRequest")
    @ResponsePayload
    public UpdateHotelResponse updateHotel(@RequestPayload UpdateHotelRequest updateHotelRequest) {
        UpdateHotelResponse response = new UpdateHotelResponse();
        HotelDto hotelDto = hotelService.updateHotel(updateHotelRequest.getHotelDto());
        response.setHotelDto(hotelDto);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "deleteHotelRequest")
    @ResponsePayload
    public DeleteHotelResponse deleteHotel(@RequestPayload DeleteHotelRequest deleteHotelRequest) {
        DeleteHotelResponse response = new DeleteHotelResponse();
        boolean result = hotelService.deleteHotel(deleteHotelRequest.getHotelId());
        response.setResult(result);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }


    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getHotelListRequest")
    @ResponsePayload
    public GetHotelListResponse getHotelList(@RequestPayload GetHotelListRequest getHotelListRequest) {
        GetHotelListResponse response = new GetHotelListResponse();
        PageRequest pageRequest = PageRequest.of(getHotelListRequest.getPage(), getHotelListRequest.getPageSize(), Sort.by(HotelValidator.HOTEL_NAME_FIELD));
        HotelListDto hotelListDto = hotelService.getHotelList(getHotelListRequest.getHotelName(), pageRequest);
        response.setResult(hotelListDto);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }


    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "addHotelAmenityRequest")
    @ResponsePayload
    public AddHotelAmenityResponse addHotelAmenity(@RequestPayload AddHotelAmenityRequest request) {
        AddHotelAmenityResponse response = new AddHotelAmenityResponse();
        HotelAmenityDto hotelAmenityDto = hotelAmenityService.addAmenityToHotel(request.getHotelAmenityDto());
        response.setHotelAmenityDto(hotelAmenityDto);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "deleteHotelAmenityRequest")
    @ResponsePayload
    public DeleteHotelAmenityResponse deleteHotelAmenity(@RequestPayload DeleteHotelAmenityRequest request) {
        DeleteHotelAmenityResponse response = new DeleteHotelAmenityResponse();
        boolean result = hotelAmenityService.deleteAmenityFromHotel(request.getHotelAmenityDto());
        response.setResult(result);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getHotelAmenityListRequest")
    @ResponsePayload
    public GetHotelAmenityListResponse getHotelAmenityListRequest(@RequestPayload GetHotelAmenityListRequest request) {
        GetHotelAmenityListResponse response = new GetHotelAmenityListResponse();
        List<AmenityDto> amenityDtoList = amenityService.findAmenitiesByHotelId(request.getHotelId());
        response.getAmenityListDto().addAll(amenityDtoList);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getAmenityListRequest")
    @ResponsePayload
    public GetAmenityListResponse getAmenityListRequest(@RequestPayload GetAmenityListRequest request) {
        GetAmenityListResponse response = new GetAmenityListResponse();
        List<AmenityDto> amenityDtoList = amenityService.getAmenityList();
        response.getAmenityListDto().addAll(amenityDtoList);
        response.setStatusCode(OK.value());
        response.setMessage(OK.name());
        return response;
    }


}
