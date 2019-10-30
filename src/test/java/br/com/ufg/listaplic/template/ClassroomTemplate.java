package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Classroom;

import java.util.UUID;

public class ClassroomTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String INSTRUCTOR_ID = "instructorId";

    public enum TYPES {
        CLASSROOM,
        CLASSROOM_WITH_ID
    }

    @Override
    public void load() {
        buildClassroomTemplate();
        buildClassroomWithIdTemplate();
    }

    private void buildClassroomTemplate() {
        Fixture.of(Classroom.class).addTemplate(TYPES.CLASSROOM.name(), new Rule() {{
            add(NAME, "Engenharia de Software - Turma A");
            add(CODE, "FLA0214");
            add(SUBJECT_CODE, "ARQSOFT2019-1");
            add(INSTRUCTOR_ID, "5da3453a5718e904108acc25");
        }});
    }

    private void buildClassroomWithIdTemplate() {
        Fixture.of(Classroom.class).addTemplate(TYPES.CLASSROOM_WITH_ID.name()).inherits(TYPES.CLASSROOM.name(), new Rule(){{
            add(ID, UUID.randomUUID());
        }});
    }

}
