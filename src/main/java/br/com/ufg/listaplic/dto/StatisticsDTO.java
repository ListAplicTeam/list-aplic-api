package br.com.ufg.listaplic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@ApiModel(
        value = "Statistics",
        description = "Statistics model."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsDTO {

    @ApiModelProperty(
            value = "Percentage of answered lists by classroom",
            dataType = "float",
            example = "0.75"
    )
    private Float completionPercentage;

    @ApiModelProperty(
            value = "Instructor's 5 most applied questions",
            dataType = "List[br.com.ufg.listaplic.dto.QuestionCountDTO]"
    )
    private List<QuestionCountDTO> topFiveQuestions;

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

    public List<QuestionCountDTO> getTopFiveQuestions() {
        return topFiveQuestions;
    }

    public void setTopFiveQuestions(List<QuestionCountDTO> topFiveQuestions) {
        this.topFiveQuestions = topFiveQuestions;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
