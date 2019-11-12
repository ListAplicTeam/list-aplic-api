package br.com.ufg.listaplic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
}
