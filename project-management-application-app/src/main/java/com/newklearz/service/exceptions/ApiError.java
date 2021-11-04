package com.newklearz.service.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError implements Serializable
{
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private final LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<Violation> violations = new ArrayList<>();

    public ApiError()
    {

        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message)
    {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, String debugMessage)
    {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public HttpStatus getStatus()
    {
        return status;
    }

    public void setStatus(HttpStatus status)
    {
        this.status = status;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDebugMessage()
    {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage)
    {
        this.debugMessage = debugMessage;
    }

    public List<Violation> getViolations()
    {
        return violations;
    }

    public void setViolations(List<Violation> violations)
    {
        this.violations = violations;
    }
}
