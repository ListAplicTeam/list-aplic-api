package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel(
        value = "Application",
        description = "Model of a application."
)
public class ListApplicationDTO {

    @ApiModelProperty(
            value = "Application identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            readOnly = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "Group identification UUID",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            required = true
    )
    @NotNull(message = "groupId must be provided")
    private UUID groupId;

    @ApiModelProperty(
            value = "List identification UUID",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            required = true
    )
    @NotNull(message = "listId must be provided")
    private UUID listId;


}
