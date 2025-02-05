package com.example.number_classifier_api.model;

public class ErrorResponse {
    private final String number;
    private final boolean error;

    public ErrorResponse(String number, boolean error) {
        this.number = number;
        this.error = error;
    }

    public String getNumber() {
        return number;
    }

    public boolean isError() {
        return error;
    }
}
