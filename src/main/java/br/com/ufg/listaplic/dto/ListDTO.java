package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel(
        value = "List",
        description = "Model of a List."
)
public class ListDTO {

    @ApiModelProperty(
            value = "List identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            readOnly = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "List's name",
            dataType = "string",
            example = "Questões sobre expressividade",
            required = true
    )
    @NotNull(message = "name must be provided")
    private String name;

    @ApiModelProperty(
            value = "List's user",
            dataType = "string",
            example = "professor@ufg.br",
            required = true
    )
    @NotNull(message = "user must be provided")
    private String user;

    @ApiModelProperty(
            value = "Subject identification code",
            dataType = "string",
            example = "ARQSOFT2019-1",
            required = true
    )
    @NotNull(message = "subjectCode must be provided")
    private String subjectCode;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}
