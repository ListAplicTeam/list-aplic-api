package br.com.ufg.listaplic.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@IdClass(QuestionCountKey.class)
@Table(name = "question_count")
public class QuestionCount {

    @Id
    @Column(name = "question")
    private UUID question;

    @Id
    @Column(name = "instructor")
    private String instructor;

    @Column(name = "counter")
    private Integer counter;

    public QuestionCount(UUID question, String instructor, Integer counter) {
        this.question = question;
        this.instructor = instructor;
        this.counter = counter;
    }

    public UUID getQuestion() {
        return question;
    }

    public void setQuestion(UUID question) {
        this.question = question;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
