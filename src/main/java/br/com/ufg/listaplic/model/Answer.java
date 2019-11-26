package br.com.ufg.listaplic.model;

import br.com.ufg.listaplic.dto.AnswerStatusType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "answer")
@SqlResultSetMapping(
        name = "answer_count",
        columns = {
                @ColumnResult(name = "application", type = String.class),
                @ColumnResult(name = "quantity", type = Integer.class)
        }
)
@NamedNativeQuery(
        name = "Answer.findAnswerCountsByClassroomId",
        query = "SELECT CAST(a.application_id AS VARCHAR(40)) AS application, CAST(COUNT(DISTINCT a.user_id) AS INTEGER) AS quantity FROM answer a " +
                "INNER JOIN application p ON p.id = a.application_id " +
                "WHERE p.classroom_id = :classroomId " +
                "GROUP BY a.application_id",
        resultSetMapping = "answer_count")
public class Answer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "application_id", nullable = false)
    private UUID applicationId;

    @Column(name = "question_id", nullable = false)
    private UUID questionId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "status_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerStatusType statusType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(AnswerStatusType statusType) {
        this.statusType = statusType;
    }
}
