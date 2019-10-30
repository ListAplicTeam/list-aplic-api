package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel(
        value = "Apply",
        description = "Model of a apply list."
)
public class ApplyDTO {

    @ApiModelProperty(
            value = "All Classroom flag",
            dataType = "boolean",
            example = "true",
            required = false
    )
    private Boolean allClassroom;

    @ApiModelProperty(
            value = "Group to list be applied",
            dataType = "string",
            example = "116181-b625151",
            required = true
    )
    @NotNull(message = "group must be provided")
    private String group;

    @ApiModelProperty(
            value = "Classroom's id",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            required = true
    )
    @NotNull(message = "classroomId must be provided")
    private UUID classroomId;

    @ApiModelProperty(
            value = "List's id",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            required = true
    )
    @NotNull(message = "listId must be provided")
    private UUID listId;

    public Boolean getAllClassroom() {
        return allClassroom;
    }

    public void setAllClassroom(Boolean allClassroom) {
        this.allClassroom = allClassroom;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public UUID getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(UUID classroomId) {
        this.classroomId = classroomId;
    }

    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }
}
