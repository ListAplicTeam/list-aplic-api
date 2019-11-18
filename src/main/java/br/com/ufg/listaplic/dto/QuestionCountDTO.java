package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModelProperty;

public class QuestionCountDTO {

    @ApiModelProperty(
            value = "Question",
            dataType = "br.com.ufg.listaplic.dto.QuestionDTO"
    )
    private QuestionDTO question;

    @ApiModelProperty(
            value = "Counter of how many times the question has been applied",
            dataType = "integer"
    )
    private Integer counter;

    public QuestionCountDTO(QuestionDTO question, Integer counter) {
        this.question = question;
        this.counter = counter;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
