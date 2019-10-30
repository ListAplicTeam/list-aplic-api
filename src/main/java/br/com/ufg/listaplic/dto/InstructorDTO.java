package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModelProperty;

public class InstructorDTO {

    @ApiModelProperty(
            value = "Instructor identification id.",
            dataType = "string",
            example = "5da3453a5718e904108acc25"
    )
    private String id;

    @ApiModelProperty(
            value = "Instructor's name",
            dataType = "string",
            example = "Isaias Tavares da Silva Neto"
    )
    private String name;

    @ApiModelProperty(
            value = "Instructor's email",
            dataType = "string",
            example = "professor@ufg.br"
    )
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
