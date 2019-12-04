package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class ListApplicationDTOTemplate implements TemplateLoader {

    private String ID = "id";
    private String GROUP_ID = "groupId";
    private String LIST_ID = "listId";
    private String STATUS = "status";
    private String APPLICATION_DATE_TIME = "applicationDateTime";
    private String START_DATE = "startDate";
    private String FINAL_DATE = "finalDate";
    private String STUDENT_LIST = "studentList";
    private String ANSWER_LIST = "answerList";
    private String NAME = "name";
    private String SUBJECTS = "subjects";
    private String KNOWLEDGE_AREAS = "knowledgeAreas";

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
            add(NAME, "Lista Teste");
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.ENCERRADA);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
            add(START_DATE, LocalDateTime.now());
            add(FINAL_DATE, LocalDateTime.now().plusDays(7));
            add(STUDENT_LIST, has(1).of(StudentDTO.class, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name()));
            add(SUBJECTS, has(1).of(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(KNOWLEDGE_AREAS, has(1).of(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
        }});
    }

    private void buildApplicationDTOWithAnswers() {
        Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.APPLICATIONDTO_WITH_ANSWERS.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(NAME, "Lista Teste");
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.ENCERRADA);
            add(APPLICATION_DATE_TIME, Timestamp.from(Instant.now()));
            add(START_DATE, LocalDateTime.now());
            add(FINAL_DATE, LocalDateTime.now().plusDays(7));
            add(STUDENT_LIST, has(1).of(StudentDTO.class, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name()));
            add(ANSWER_LIST, has(2).of(AnswerDTO.class, AnswerDTOTemplate.TYPES.ANSWER_DTO.name()));
            add(SUBJECTS, has(1).of(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(KNOWLEDGE_AREAS, has(1).of(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
        }});
    }

    private void buildListApplicationTemplate() {
        Fixture.of(ListApplicationDTO.class).addTemplate(TYPES.LIST_APPLICATION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(NAME, "Lista Teste");
            add(GROUP_ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(STATUS, ApplicationListStatus.EM_ANDAMENTO);
            add(APPLICATION_DATE_TIME, new Timestamp(System.currentTimeMillis()));
            add(START_DATE, LocalDateTime.now());
            add(FINAL_DATE, LocalDateTime.now().plusDays(7));
            add(SUBJECTS, has(1).of(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(KNOWLEDGE_AREAS, has(1).of(AreaDoConhecimentoDTO.class, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name()));
        }});
    }
}
