package com.abnambro.customer.account.manager.exception;

import com.abnambro.customer.account.manager.base.BaseControllerTest;
import com.abnambro.customer.account.manager.exception.user.UserNameAlreadyExistsException;
import com.abnambro.customer.account.manager.exception.user.UserNotFoundException;
import com.abnambro.customer.account.manager.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class GlobalExceptionHandlerTest extends BaseControllerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void givenUnsupportedMediaType_whenThrowHttpMediaTypeNotSupportedException_thenReturnErrorResponse() {

        // Given
        HttpMediaTypeNotSupportedException mockException = new HttpMediaTypeNotSupportedException("Unsupported media type");

        // When
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(mockException.getContentType() + "Unsupported media type is not supported. Supported media types are "
                + mockException.getSupportedMediaTypes().stream().map(MediaType::toString).collect(Collectors.joining(", ")));

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("Invalid JSON")
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .build();

        // Then
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleHttpMediaTypeNotSupported(
                mockException, mockException.getHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, null);

        Assertions.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
    }


    @Test
    void givenMalformedJsonRequest_whenThrowHttpMessageNotReadableException_thenReturnErrorResponse() {

        // Given
        HttpInputMessage httpInputMessage = Mockito.mock(HttpInputMessage.class);
        HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Malformed JSON request", httpInputMessage);

        // When
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(mockException.getMessage());

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("Malformed JSON request")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        // Then
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleHttpMessageNotReadable(
                mockException, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void givenValidationErrors_whenThrowMethodArgumentNotValidException_thenReturnErrorResponse() throws NoSuchMethodException {

        // Given
        Method method = GlobalExceptionHandlerTest.class.getDeclaredMethod("givenValidationErrors_whenThrowMethodArgumentNotValidException_thenReturnErrorResponse");
        int parameterIndex = -1;

        MethodParameter mockParameter = new MethodParameter(method, parameterIndex);
        BindingResult mockBindingResult = new BeanPropertyBindingResult(null, null);
        MethodArgumentNotValidException mockException = new MethodArgumentNotValidException(mockParameter, mockBindingResult);

        // When
        List<String> details = mockException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .toList();

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("Validation Errors")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        // Then
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleMethodArgumentNotValid(
                mockException, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());


    }

    @Test
    void givenMissingServletRequestParameter_whenThrowMissingServletRequestParameterException_thenReturnErrorResponse() {

        // Given
        MissingServletRequestParameterException mockException = new MissingServletRequestParameterException(
                "parameterName", "parameterType");

        // When
        List<String> details = new ArrayList<>();
        details.add(mockException.getParameterName() + " parameter is missing");

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("Validation Errors")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        // Then
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleMissingServletRequestParameter(
                mockException, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    void givenUserNotFoundException_whenThrowUserNotFoundException_thenReturnErrorResponse() {

        // Given
        UserNotFoundException mockException = new UserNotFoundException("User not found");

        // When
        List<String> details = new ArrayList<>();
        details.add(mockException.getMessage());

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("User Not Found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .build();

        // Then
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleUserNotFoundException(mockException);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    void givenEmailAlreadyExistsException_whenThrowUserNamelAlreadyExistsException_thenReturnErrorResponse() {

        // Given
        UserNameAlreadyExistsException mockException = new UserNameAlreadyExistsException("Username already exists");

        // When
        List<String> details = new ArrayList<>();
        details.add(mockException.getMessage());

        ErrorResponse expectedErrorResponse = ErrorResponse.builder()
                .message("Email Already Exists")
                .statusCode(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .build();

        // Then
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleEmailAlreadyExistsException(mockException);

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

    }



}
