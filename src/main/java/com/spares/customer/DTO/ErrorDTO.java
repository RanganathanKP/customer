package com.spares.customer.DTO;

public class ErrorDTO {

    private String type;
    private String errorMessage;

    public ErrorDTO() {}

    public ErrorDTO(String type, String errorMessage) {
        this.type = type;
        this.errorMessage = errorMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
