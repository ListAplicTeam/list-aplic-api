package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class ListApplicationDTOTemplate implements TemplateLoader {

    private String ID = "id";
    private String GROUP_ID = "groupId";
    private String LIST_ID = "listId";
    private String STATUS = "status";
    private String APPLICATION_DATE_TIME = "applicationDateTime";
    private String STUDENT_LIST = "studentList";
    private String ANSWER_LIST = "answerList";

    public enum TYPES {
        APPLICATIONDTO_WITHOUT_ANSWERS,
        APPLICATIONDTO_WITH_ANSWERS,
        LIST_APPLICATION
    }

    @Override
    public void load() {
        buildApplicationDTOWithoutAnswers();
        buildApplicationDTOWithAnswers();
        buildListApplicationTemplate();
    }

    private void buildApplicationDTOWithoutAnswers() {
        Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.APPLICATIONDTO_WITHOUT_ANSWERS.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.ENCERRADA);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
            add(STUDENT_LIST, has(1).of(StudentDTO.class, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name()));
        }});
    }

    private void buildApplicationDTOWithAnswers() {
        Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.APPLICATIONDTO_WITH_ANSWERS.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.ENCERRADA);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
            add(STUDENT_LIST, has(1).of(StudentDTO.class, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name()));
            add(ANSWER_LIST, has(2).of(AnswerDTO.class, AnswerDTOTemplate.TYPES.ANSWER_DTO.name()));
        }});
    }

    private void buildListApplicationTemplate() {
        Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.LIST_APPLICATION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.EM_ANDAMENTO);
            add(APPLICATION_DATE_TIME, new Timestamp(System.currentTimeMillis()));
        }});
    }
}
