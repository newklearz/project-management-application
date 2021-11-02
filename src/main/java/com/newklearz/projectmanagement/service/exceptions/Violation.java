package com.newklearz.projectmanagement.service.exceptions;

import java.io.Serializable;

public class Violation implements Serializable {

    private String field;

    private String message;

    private String receivedValue;

    public Violation(String field, String message, String receivedValue) {
        this.field = field;
        this.message = message;
        this.receivedValue = receivedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceivedValue() {
        return receivedValue;
    }

    public void setReceivedValue(String receivedValue) {
        this.receivedValue = receivedValue;
    }
}
