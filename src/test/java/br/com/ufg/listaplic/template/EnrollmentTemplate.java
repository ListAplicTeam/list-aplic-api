package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.Enrollment;
import br.com.ufg.listaplic.model.Student;

import java.util.UUID;

public class EnrollmentTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String CLASSROOM = "classroom";
    private static final String STUDENT = "student";

    public enum TYPES {
        ENROLLMENT
    }

    @Override
    public void load() {
        buildEnrollmentTemplate();
    }

    private void buildEnrollmentTemplate() {
        Fixture.of(Enrollment.class).addTemplate(TYPES.ENROLLMENT.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(CLASSROOM,  one(Classroom.class, ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name()));
            add(STUDENT, one(Student.class, StudentTemplate.TYPES.STUDENT_WITH_ID.name()));
        }});
    }

}
