package br.com.ufg.listaplic.model;

public enum Role {

    DISCENTE("Discente"),
    DOCENTE("Docente");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
