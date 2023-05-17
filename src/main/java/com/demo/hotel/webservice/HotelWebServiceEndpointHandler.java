package com.demo.hotel.webservice;

import com.demo.hotel.exception.InvalidHotelFieldException;
import com.demo.hotel.webservice.dto.ResponseStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Aspect
@Component
public class HotelWebServiceEndpointHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HotelWebServiceEndpointHandler.class);

    @Around("execution(* com.demo.hotel.webservice.HotelWebServiceEndpoint.*(..))")
    public Object hotelWebServiceAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOG.info("Call endpoint >> {}", proceedingJoinPoint.getSignature().getName());
        Object value;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            Class<?> returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getReturnType();
            value = handleError(returnType, e);
            LOG.error("Call endpoint error: ", e);
        }
        LOG.info("Call endpoint << {}", proceedingJoinPoint.getSignature().getName());
        return value;
    }


    Object handleError(Class<?> returnType, Throwable e) {
        Object response = null;
        try {
            Object newReturn = returnType.newInstance();
            if (newReturn instanceof ResponseStatus) {
                ResponseStatus res = (ResponseStatus) newReturn;
                res.setMessage(e.getMessage());
                res.setStatusCode(INTERNAL_SERVER_ERROR.value());
                if(e instanceof InvalidHotelFieldException){
                    res.setStatusCode(BAD_REQUEST.value());
                }
                response = res;
            }
        } catch (Exception ex) {
            LOG.error("Error creating generic response", ex);
        }
        return response;
    }
}
