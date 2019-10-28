package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.UUID;

@ApiModel(
        value = "List",
        description = "Model of a List."
)
public class ListDTO {

    @ApiModelProperty(
            value = "List identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID id;

    @ApiModelProperty(
            value = "List's name",
            dataType = "string",
            example = "Questões sobre expressividade"
    )
    private String name;

    @ApiModelProperty(
            value = "List's user",
            dataType = "string",
            example = "professor@ufg.br"
    )
    private String user;

    @ApiModelProperty(
            value = "Subject identification code",
            dataType = "string",
            example = "ARQSOFT2019-1"
    )
    private String subjectCode;

    @ApiModelProperty(
            value = "List's Questions",
            dataType = "List[br.com.ufg.listaplic.dto.QuestionDTO]"
    )
    private List<QuestionDTO> questions;

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

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
