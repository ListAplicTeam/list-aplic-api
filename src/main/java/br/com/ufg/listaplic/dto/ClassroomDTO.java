package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel(
        value = "Classroom",
        description = "Model of a Classroom."
)
public class ClassroomDTO {

    @ApiModelProperty(
            value = "Classroom identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            readOnly = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "Classroom's name",
            dataType = "string",
            example = "Engenharia de Software - Turma A",
            required = true
    )
    @NotNull(message = "name must be provided")
    private String name;

    @ApiModelProperty(
            value = "Classroom identification code",
            dataType = "string",
            example = "FLA0214",
            readOnly = true
    )
    private String code;

    @ApiModelProperty(
            value = "Subject identification code",
            dataType = "string",
            example = "ARQSOFT2019-1",
            required = true
    )
    @NotNull(message = "subjectCode must be provided")
    private String subjectCode;

    @ApiModelProperty(
            value = "Instructor identification UUID",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            required = true
    )
    @NotNull(message = "instructorId must be provided")
    private UUID instructorId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public UUID getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(UUID instructorId) {
        this.instructorId = instructorId;
    }
}
