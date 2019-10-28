package br.com.ufg.listaplic.dto.listelab;

import java.util.UUID;

public class ObjetivasIntegrationDTO {

    private UUID id;
    private String enunciado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
