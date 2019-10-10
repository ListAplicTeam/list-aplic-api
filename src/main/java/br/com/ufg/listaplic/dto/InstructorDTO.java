package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

public class InstructorDTO {

    @ApiModelProperty(
            value = "Student identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Student's name",
            dataType = "string",
            example = "Isaias Tavares da Silva Neto"
    )
    private String name;

    @ApiModelProperty(
            value = "Student's email",
            dataType = "string",
            example = "isaias_neto@discente.ufg.br"
    )
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
