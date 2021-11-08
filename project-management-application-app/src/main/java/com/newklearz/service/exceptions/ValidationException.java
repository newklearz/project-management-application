package com.newklearz.service.exceptions;

public class ValidationException extends RuntimeException
{
    private ApiError apiError;

    public ValidationException(ApiError apiError)
    {
        this.apiError = apiError;
    }

    public ApiError getApiError()
    {
        return apiError;
    }

    public void setApiError(ApiError apiError)
    {
        this.apiError = apiError;
    }
}
