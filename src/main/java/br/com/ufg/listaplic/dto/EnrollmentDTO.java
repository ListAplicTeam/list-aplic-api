package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "Enrollment",
        description = "Model of a Enrollment."
)
public class EnrollmentDTO {

    @ApiModelProperty(
            value = "Classroom identification code",
            dataType = "string",
            example = "FLA0214"
    )
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
