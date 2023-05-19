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

    enum ErrorMessage {
        DATABASE_CONNECTION("persistence connection error, please try again later"),
        CONSTRAINT("persistence constraint error, please check the information you are trying to save"),
        NETWORK("network error, please try again later"),
        UNEXPECTED("system error, please try again later");
        private final String message;

        ErrorMessage(String s) {
            this.message = s;
        }

        public String message() {
            return message;
        }

    }

    @Around("execution(* com.demo.hotel.webservice.HotelWebServiceEndpoint.*(..))")
    public Object hotelWebServiceAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOG.info("Call endpoint >> {}", proceedingJoinPoint.getSignature().getName());
        Object value;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            Class<?> returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getReturnType();
            value = handleError(returnType, e);
            // login original error
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
                setResponseStatusByException(res, e);
                response = res;
            }
        } catch (Exception ex) {
            LOG.error("Error creating generic response", ex);
        }
        return response;
    }

    void setResponseStatusByException(ResponseStatus responseStatus, Throwable e) {
        if (e instanceof InvalidHotelFieldException) {
            responseStatus.setStatusCode(BAD_REQUEST.value());
            responseStatus.setMessage(e.getMessage());
        } else if (e instanceof org.springframework.transaction.CannotCreateTransactionException) {
            responseStatus.setMessage(ErrorMessage.DATABASE_CONNECTION.message);
            responseStatus.setStatusCode(INTERNAL_SERVER_ERROR.value());
        } else if (e instanceof org.springframework.dao.DataIntegrityViolationException) {
            responseStatus.setMessage(ErrorMessage.CONSTRAINT.message);
            responseStatus.setStatusCode(INTERNAL_SERVER_ERROR.value());
        } else if (e instanceof java.net.ConnectException || e instanceof java.net.SocketTimeoutException) {
            responseStatus.setMessage(ErrorMessage.NETWORK.message);
            responseStatus.setStatusCode(INTERNAL_SERVER_ERROR.value());
        } else if (e instanceof Exception) {
            responseStatus.setMessage(ErrorMessage.UNEXPECTED.message);
            responseStatus.setStatusCode(INTERNAL_SERVER_ERROR.value());
        } else {
            responseStatus.setMessage(ErrorMessage.UNEXPECTED.message);
            responseStatus.setStatusCode(INTERNAL_SERVER_ERROR.value());
        }
    }
}
