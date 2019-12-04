package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.Enrollment;
import com.google.common.collect.Sets;

import java.util.UUID;

public class ClassroomTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CODE = "code";
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String INSTRUCTOR_ID = "instructorId";
    private static final String ENROLLMENTS = "enrollments";

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
            add(NAME, "Integração 2 2018-1");
            add(CODE, "CWS4558");
            add(SUBJECT_CODE, "INF0089");
            add(INSTRUCTOR_ID, "5da3453a5718e904108acc25");
            add(ENROLLMENTS, Sets.newHashSet(has(2).of(Enrollment.class, EnrollmentTemplate.TYPES.ENROLLMENT.name())));
        }});
    }

    private void buildClassroomWithIdTemplate() {
        Fixture.of(Classroom.class).addTemplate(TYPES.CLASSROOM_WITH_ID.name()).inherits(TYPES.CLASSROOM.name(), new Rule(){{
            add(ID, UUID.fromString("dd4164a8-4cf1-467a-af34-0d48d9d2b699"));
        }});
    }

}
