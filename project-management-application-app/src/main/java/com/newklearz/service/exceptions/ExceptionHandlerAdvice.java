package com.newklearz.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(UserCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onUserCredentialsException(UserCredentialsException ex)
    {
        return new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage());

    }

    @ExceptionHandler(BoardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onBoardRetrievalException(BoardException ex)
    {
        return new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}