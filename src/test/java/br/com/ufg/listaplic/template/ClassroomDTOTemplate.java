package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.ClassroomDTO;

import java.util.UUID;

public class ClassroomDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String INSTRUCTOR_ID = "instructorId";

    public enum TYPES {
        CLASSROOM,
        CLASSROOM_WITH_ID,
        ANOTHER_CLASSROOM
    }

    @Override
    public void load() {
        buildClassroomTemplate();
        buildClassroomWithIdTemplate();
        buildAnotherClassroomTemplate();
    }

    private void buildClassroomTemplate() {
        Fixture.of(ClassroomDTO.class).addTemplate(TYPES.CLASSROOM.name(), new Rule() {{
            add(NAME, "Integração 2 2018-1");
            add(CODE, "CWS4558");
            add(SUBJECT_CODE, "INF0089");
            add(INSTRUCTOR_ID, "5da3453a5718e904108acc25");
        }});
    }

    private void buildClassroomWithIdTemplate() {
        Fixture.of(ClassroomDTO.class).addTemplate(TYPES.CLASSROOM_WITH_ID.name()).inherits(TYPES.CLASSROOM.name(), new Rule(){{
            add(ID, UUID.fromString("dd4164a8-4cf1-467a-af34-0d48d9d2b699"));
        }});
    }

    private void buildAnotherClassroomTemplate() {
        Fixture.of(ClassroomDTO.class).addTemplate(TYPES.ANOTHER_CLASSROOM.name(), new Rule() {{
            add(NAME, "Another Classroom");
            add(CODE, "ANO0123");
            add(SUBJECT_CODE, "ANOT_CODE_2019-1");
            add(INSTRUCTOR_ID, "5da3453a5718e904108acc25");
        }});
    }

}
