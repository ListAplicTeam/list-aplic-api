package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Enrollment;
import br.com.ufg.listaplic.model.Student;
import com.google.common.collect.Sets;

import java.util.UUID;

public class StudentTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ENROLLMENTS = "enrollments";

    public enum TYPES {
        STUDENT,
        STUDENT_WITH_ID,
        NEW_STUDENT
    }

    @Override
    public void load() {
        buildStudentTemplate();
        buildStudentWithIdTemplate();
        buildNewStudentTemplate();
    }

    private void buildStudentTemplate() {
        Fixture.of(Student.class).addTemplate(TYPES.STUDENT.name(), new Rule() {{
            add(NAME, "Isaias Tavares da Silva Neto");
            add(EMAIL, "isaias_neto@discente.ufg.br");
            add(PASSWORD, "1e9f258f838afe310eb0da6501e3c354");
            add(ENROLLMENTS, Sets.newHashSet(has(2).of(Enrollment.class, EnrollmentTemplate.TYPES.ENROLLMENT.name())));
        }});
    }

    private void buildNewStudentTemplate() {
        Fixture.of(Student.class).addTemplate(TYPES.NEW_STUDENT.name(), new Rule() {{
            add(NAME, "New Student");
            add(EMAIL, "new_student@discente.ufg.br");
            add(PASSWORD, "123456");
        }});
    }

    private void buildStudentWithIdTemplate() {
        Fixture.of(Student.class).addTemplate(TYPES.STUDENT_WITH_ID.name()).inherits(TYPES.STUDENT.name(), new Rule(){{
            add(ID, UUID.fromString("fa8789f1-17a8-4937-b7db-5910471cc61b"));
        }});
    }

}
