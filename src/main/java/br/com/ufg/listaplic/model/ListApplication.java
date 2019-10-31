package br.com.ufg.listaplic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplicationListStatus status = ApplicationListStatus.EM_ANDAMENTO;

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

    public ApplicationListStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationListStatus status) {
        this.status = status;
    }
}
