package br.com.ufg.listaplic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;

@ApiModel(
        value = "Statistics",
        description = "Statistics model."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsDTO {

    @ApiModelProperty(
            value = "Percentage of answered lists by classroom",
            dataType = "float",
            example = "74.3"
    )
    private Float completionPercentage;

    @ApiModelProperty(
            value = "Error message",
            dataType = "string"
    )
    private String errorMessage;

    public Float getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(Float completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
