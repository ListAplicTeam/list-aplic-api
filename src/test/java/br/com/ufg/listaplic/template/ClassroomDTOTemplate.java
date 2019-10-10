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
            add(NAME, "Engenharia de Software - Turma A");
            add(CODE, "FLA0214");
            add(SUBJECT_CODE, "ARQSOFT2019-1");
            add(INSTRUCTOR_ID, UUID.fromString("91b4a2dd-1797-48bb-8353-1231888129a1"));
        }});
    }

    private void buildClassroomWithIdTemplate() {
        Fixture.of(ClassroomDTO.class).addTemplate(TYPES.CLASSROOM_WITH_ID.name()).inherits(TYPES.CLASSROOM.name(), new Rule(){{
            add(ID, UUID.randomUUID());
        }});
    }

    private void buildAnotherClassroomTemplate() {
        Fixture.of(ClassroomDTO.class).addTemplate(TYPES.ANOTHER_CLASSROOM.name(), new Rule() {{
            add(NAME, "Another Classroom");
            add(CODE, "ANO0123");
            add(SUBJECT_CODE, "ANOT_CODE_2019-1");
            add(INSTRUCTOR_ID, UUID.fromString("91b4a2dd-1797-48bb-8353-1231888129a1"));
        }});
    }

}
