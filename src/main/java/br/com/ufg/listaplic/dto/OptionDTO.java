package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "Option",
        description = "Model of a Option."
)
public class OptionDTO {

    @ApiModelProperty(
            value = "Option's name",
            dataType = "string",
            example = "Lenda"
    )
    private String name;

}
