package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.StudentDTO;

import java.util.UUID;

public class StudentDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public enum TYPES {
        STUDENT,
        STUDENT_WITH_ID,
        ANOTHER_STUDENT
    }

    @Override
    public void load() {
        buildStudentTemplate();
        buildStudentWithIdTemplate();
        buildAnotherStudentTemplate();
    }

    private void buildStudentTemplate() {
        Fixture.of(StudentDTO.class).addTemplate(TYPES.STUDENT.name(), new Rule() {{
            add(NAME, "Isaias Tavares da Silva Neto");
            add(EMAIL, "isaias_neto@discente.ufg.br");
            add(PASSWORD, "nobodyyesdoor");
        }});
    }

    private void buildStudentWithIdTemplate() {
        Fixture.of(StudentDTO.class).addTemplate(TYPES.STUDENT_WITH_ID.name()).inherits(TYPES.STUDENT.name(), new Rule(){{
            add(ID, UUID.randomUUID());
        }});
    }

    private void buildAnotherStudentTemplate() {
        Fixture.of(StudentDTO.class).addTemplate(TYPES.ANOTHER_STUDENT.name(), new Rule() {{
            add(NAME, "Another Student");
            add(EMAIL, "another_student@discente.ufg.br");
            add(PASSWORD, "nobodyyesdoor");
        }});
    }

}
