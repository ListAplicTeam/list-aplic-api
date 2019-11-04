package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ListApplicationTemplate implements TemplateLoader {

    private String ID = "id";
    private String CLASSROOM = "classroom";
    private String LIST = "list";
    private String APPLICATION_DATE_TIME = "applicationDateTime";
    private String STATUS = "status";

    public enum TYPES {
        FINISHED_APPLICATION,
        UNFINISHED_APPLICATION
    }

    @Override
    public void load() {
        buildFinishedApplication();
        buildUnfinishedApplication();
    }

    private void buildFinishedApplication() {
        Fixture.of(ListApplication.class).addTemplate(TYPES.FINISHED_APPLICATION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(CLASSROOM, one(Classroom.class,ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name()));
            add(LIST, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.ENCERRADA);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
        }});
    }

    private void buildUnfinishedApplication() {
        Fixture.of(ListApplication.class).addTemplate(TYPES.UNFINISHED_APPLICATION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(CLASSROOM, one(Classroom.class,ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name()));
            add(LIST, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.EM_ANDAMENTO);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
        }});
    }
}
