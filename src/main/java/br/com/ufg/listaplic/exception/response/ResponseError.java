package br.com.ufg.listaplic.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

public class ResponseError {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<FieldError> fieldErrors;

    public ResponseError(final String message) {
        this.message = message;
    }

    public ResponseError(final String id, final String message, final Collection<FieldError> fieldErrors) {
        this.id = id;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public ResponseError(final String message, final Collection<FieldError> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public ResponseError(final Collection<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Collection<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}