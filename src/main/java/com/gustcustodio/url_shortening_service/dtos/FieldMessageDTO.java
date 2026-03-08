package com.gustcustodio.url_shortening_service.dtos;

public class FieldMessageDTO {

    private String fieldName;
    private String message;

    public FieldMessageDTO() {
    }

    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
