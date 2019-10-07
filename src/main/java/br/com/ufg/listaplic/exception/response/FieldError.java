package br.com.ufg.listaplic.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class FieldError implements Serializable {

    private static final long serialVersionUID = 1735798493585971506L;

    private String field;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object value;

    public FieldError(final String field, final String message, Object value) {
        this.field = field;
        this.message = message;
        this.value = value;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
