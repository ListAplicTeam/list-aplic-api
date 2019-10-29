package br.com.ufg.listaplic.model;

import br.com.ufg.listaplic.dto.ListDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "application", uniqueConstraints = @UniqueConstraint(columnNames = {"classroom_id", "list_id"}, name = "classroom_list"))
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private ListDTO list;

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

    public ListDTO getList() {
        return list;
    }

    public void setList(ListDTO list) {
        this.list = list;
    }
}
