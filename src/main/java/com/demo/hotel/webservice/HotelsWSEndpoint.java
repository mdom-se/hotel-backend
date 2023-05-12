package com.demo.hotel.webservice;

import com.demo.hotel.service.HotelService;
import com.demo.hotel.webservice.dto.AddHotelRequest;
import com.demo.hotel.webservice.dto.AddHotelResponse;
import com.demo.hotel.webservice.dto.DeleteHotelRequest;
import com.demo.hotel.webservice.dto.DeleteHotelResponse;
import com.demo.hotel.webservice.dto.GetHotelListRequest;
import com.demo.hotel.webservice.dto.GetHotelListResponse;
import com.demo.hotel.webservice.dto.GetHotelRequest;
import com.demo.hotel.webservice.dto.GetHotelResponse;
import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import com.demo.hotel.webservice.dto.UpdateHotelRequest;
import com.demo.hotel.webservice.dto.UpdateHotelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.demo.hotel.webservice.WSConfiguration.WS_TARGET_NAMESPACE;

@Endpoint
public class HotelsWSEndpoint {

    @Autowired
    private HotelService hotelService;

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getHotelRequest")
    @ResponsePayload
    public GetHotelResponse getHotel(@RequestPayload GetHotelRequest getHotelRequest){
        GetHotelResponse response = new GetHotelResponse();
        HotelDto hotelDto = hotelService.findHotelById(getHotelRequest.getHotelId());
        response.setHotelDto(hotelDto);
        return response;
    }


    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "addHotelRequest" )
    @ResponsePayload
    public AddHotelResponse addHotel(@RequestPayload AddHotelRequest addHotelRequest){
        AddHotelResponse response = new AddHotelResponse();
        HotelDto hotelDto = hotelService.createHotel(addHotelRequest.getHotelDto());
        response.setHotelDto(hotelDto);
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "updateHotelRequest")
    @ResponsePayload
    public UpdateHotelResponse updateHotel(@RequestPayload UpdateHotelRequest updateHotelRequest){
        UpdateHotelResponse response = new UpdateHotelResponse();
        HotelDto hotelDto = hotelService.updateHotel(updateHotelRequest.getHotelDto());
        response.setHotelDto(hotelDto);
        return response;
    }

    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "deleteHotelRequest")
    @ResponsePayload
    public DeleteHotelResponse deleteHotel(@RequestPayload DeleteHotelRequest deleteHotelRequest){
        DeleteHotelResponse response = new DeleteHotelResponse();
        boolean result = hotelService.deleteHotel(deleteHotelRequest.getHotelId());
        response.setResult(result);
        return response;
    }


    @PayloadRoot(namespace = WS_TARGET_NAMESPACE, localPart = "getHotelListRequest")
    @ResponsePayload
    public GetHotelListResponse getHotelList(@RequestPayload GetHotelListRequest getHotelListRequest){
        GetHotelListResponse response = new GetHotelListResponse();
        PageRequest pageRequest = PageRequest.of(getHotelListRequest.getPage(), getHotelListRequest.getSize());
        HotelListDto hotelListDto = hotelService.getHotelList(getHotelListRequest.getHotelName(), pageRequest);
        response.setHotelListDto(hotelListDto);
        return response;
    }
}
