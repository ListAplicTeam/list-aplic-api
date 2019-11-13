package br.com.ufg.listaplic.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

public class QuestionCountKey implements Serializable {

    private UUID question;

    private String instructor;

    public QuestionCountKey(UUID question, String instructor) {
        this.question = question;
        this.instructor = instructor;
    }
}
