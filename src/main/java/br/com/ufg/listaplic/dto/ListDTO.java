package br.com.ufg.listaplic.dto;

import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;
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
            value = "List Application identification UUID.",
            dataType = "string",
            example = "1291440f-c8ff-4d5c-baf7-5f843bf546bf"
    )
    private UUID listApplicationId;

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
            value = "List's difficulty level",
            dataType = "int",
            example = "3"
    )
    private int difficultyLevel;

    @ApiModelProperty(
            value = "List's subjects",
            dataType = "Set[br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO]"
    )
    private Set<DisciplinaIntegrationDTO> subjects;

    @ApiModelProperty(
            value = "List's knowledge areas",
            dataType = "Set[br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO]"
    )
    private Set<AreaDoConhecimentoDTO> knowledgeAreas;

    @ApiModelProperty(
            value = "List's tags",
            dataType = "Set[java.lang.String]"
    )
    private Set<String> tags;

    @ApiModelProperty(
            value = "List's Questions",
            dataType = "List[br.com.ufg.listaplic.dto.QuestionDTO]"
    )
    private List<QuestionDTO> questions;

    @ApiModelProperty(
            value = "List's Status",
            dataType = "br.com.ufg.listaplic.model.ApplicationListStatus",
            example = "NAO_INICIADA",
            allowableValues = "NAO_INICIADA, EM_ANDAMENTO, ENCERRADA"
    )
    private ApplicationListStatus status;

    @ApiModelProperty(
            value = "List's answer time",
            dataType = "Integer",
            example = "10"
    )
    private Integer answerTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getListApplicationId() {
        return listApplicationId;
    }

    public void setListApplicationId(UUID listApplicationId) {
        this.listApplicationId = listApplicationId;
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

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Set<DisciplinaIntegrationDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<DisciplinaIntegrationDTO> subjects) {
        this.subjects = subjects;
    }

    public Set<AreaDoConhecimentoDTO> getKnowledgeAreas() {
        return knowledgeAreas;
    }

    public void setKnowledgeAreas(Set<AreaDoConhecimentoDTO> knowledgeAreas) {
        this.knowledgeAreas = knowledgeAreas;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public ApplicationListStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationListStatus status) {
        this.status = status;
    }

    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
        this.answerTime = answerTime;
    }
}
