package br.com.ufg.listaplic.dto;

import br.com.ufg.listaplic.model.ApplicationListStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApiModel(
        value = "Application",
        description = "Model of a application."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @NotNull(message = "status must be provided")
    private ApplicationListStatus status;

    @NotNull(message = "applicationDateTime must be provided")
    private Timestamp applicationDateTime;

    @ApiModelProperty(
            value = "List's start date",
            example = "2019-11-11 00:00:00",
            required = true
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "startDate must be provided")
    private LocalDateTime startDate;

    @ApiModelProperty(
            value = "List's final date",
            example = "2019-11-11 23:59:59",
            required = true
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "finalDate must be provided")
    private LocalDateTime finalDate;

    @ApiModelProperty(
            value = "Students",
            dataType = "List[br.com.ufg.listaplic.dto.StudentDTO]",
            required = true
    )
    @NotNull(message = "studentList must be provided")
    private List<StudentDTO> studentList;

    @ApiModelProperty(
            value = "Anwsers",
            dataType = "List[br.com.ufg.listaplic.dto.AnswerDTO]"
    )
    private List<AnswerDTO> answerList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public UUID getListId() {
        return listId;
    }

    public void setListId(UUID listId) {
        this.listId = listId;
    }

    public ApplicationListStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationListStatus status) {
        this.status = status;
    }

    public Timestamp getApplicationDateTime() {
        return applicationDateTime;
    }

    public void setApplicationDateTime(Timestamp applicationDateTime) {
        this.applicationDateTime = applicationDateTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }

    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDTO> answerList) {
        this.answerList = answerList;
    }
}
