package com.demo.hotel.webservice;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.service.AmenityService;
import com.demo.hotel.service.HotelAmenityService;
import com.demo.hotel.service.HotelService;
import com.demo.hotel.webservice.dto.AddHotelRequest;
import com.demo.hotel.webservice.dto.AddHotelResponse;
import com.demo.hotel.webservice.dto.HotelDto;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.ResponseActions;
import org.springframework.ws.test.server.ResponseMatcher;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@WebServiceServerTest
@MockBeans(value = {@MockBean(AmenityService.class), @MockBean(HotelAmenityService.class)})
@Import({AnnotationAwareAspectJAutoProxyCreator.class, HotelWebServiceEndpointHandler.class}) // activate aspect
class HotelWebServiceEndpointHandlerTest {

    @Autowired
    private MockWebServiceClient client;


    @MockBean
    private HotelService hotelService;


    private final Jaxb2Marshaller marshaller = marshaller();


    @Test
    void hotelWebServiceAroundAdvice() {
        // Scenario setup
        AddHotelRequest request = new AddHotelRequest();
        request.setHotelDto(new HotelDto());

        HotelDto expectedHotelDto = new HotelDto();
        expectedHotelDto.setHotelId(1L);
        when(hotelService.createHotel(any(HotelDto.class))).thenReturn(expectedHotelDto);

        AddHotelResponse expectedResult = new AddHotelResponse();
        expectedResult.setHotelDto(expectedHotelDto);
        expectedResult.setMessage(HttpStatus.OK.name());
        expectedResult.setStatusCode(HttpStatus.OK.value());

        // test
        ResponseActions result = client.sendRequest(createRequest(request));

        //verify result
        result.andExpect(createExpectedResult(expectedResult));
    }
    @Test
    void hotelWebServiceAroundAdvice_invalidHotelFieldException() {
        // Scenario setup
        AddHotelRequest request = new AddHotelRequest();
        request.setHotelDto(new HotelDto());
        when(hotelService.createHotel(any(HotelDto.class))).thenThrow(new InvalidHotelFieldException("error"));

        AddHotelResponse expectedResult = new AddHotelResponse();
        expectedResult.setMessage("error");
        expectedResult.setStatusCode(HttpStatus.BAD_REQUEST.value());
        // test
        ResponseActions result = client.sendRequest(createRequest(request));

        //verify result
        result.andExpect(createExpectedResult(expectedResult));
    }

    @Test
    void hotelWebServiceAroundAdvice_exception() {
        // Scenario setup
        AddHotelRequest request = new AddHotelRequest();
        request.setHotelDto(new HotelDto());
        when(hotelService.createHotel(any(HotelDto.class))).thenThrow(new RuntimeException("general-error"));

        AddHotelResponse expectedResult = new AddHotelResponse();
        expectedResult.setMessage("general-error");
        expectedResult.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        // test
        ResponseActions result = client.sendRequest(createRequest(request));

        //verify result
        result.andExpect(createExpectedResult(expectedResult));
    }

    public RequestCreator createRequest(Object request) {
        return withPayload(marshallXml(request));
    }

    public ResponseMatcher createExpectedResult(Object expectedResult) {
        return payload(marshallXml(expectedResult));
    }

    private Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.demo.hotel.webservice.dto");
        return marshaller;
    }

    private <T> StringSource marshallXml(final T obj) {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        marshaller.marshal(obj, result);
        return new StringSource(sw.toString());
    }

}