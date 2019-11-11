package br.com.ufg.listaplic.dto;

import java.util.UUID;

public class AnswerCountDTO {

    private UUID application;

    private Integer quantity;

    public UUID getApplication() {
        return application;
    }

    public void setApplication(UUID application) {
        this.application = application;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
