package br.com.ufg.listaplic.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "application")
public class ListApplication {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @Column(name = "list_id", nullable = false)
    private UUID list;

    @Column(name = "application_date_time", nullable = false)
    private Timestamp applicationDateTime;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "final_date", nullable = false)
    private LocalDateTime finalDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationListStatus status = ApplicationListStatus.NAO_INICIADA;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public UUID getList() {
        return list;
    }

    public void setList(UUID list) {
        this.list = list;
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

    public ApplicationListStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationListStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("listId", this.list)
                .append("applicationDateTime", this.applicationDateTime)
                .append("startDate", this.startDate)
                .append("finalDate", this.finalDate)
                .append("status", this.status)
                .toString();
    }
}
