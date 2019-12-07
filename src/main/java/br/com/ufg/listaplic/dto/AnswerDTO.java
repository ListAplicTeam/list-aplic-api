package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

public class AnswerDTO {

    @ApiModelProperty(
            value = "List identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID listId;

    @ApiModelProperty(
            value = "Question identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID questionId;

    @ApiModelProperty(
            value = "Answer",
            dataType = "string",
            example = "Lenda"
    )
    private String answer;

    @ApiModelProperty(
            value = "Answer status",
            dataType = "string",
            example = "DRAFT",
            allowableValues = "DRAFT, SAVE"
    )
    private AnswerStatusType status;

    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerStatusType getStatus() {
        return status;
    }

    public void setStatus(AnswerStatusType status) {
        this.status = status;
    }
}
