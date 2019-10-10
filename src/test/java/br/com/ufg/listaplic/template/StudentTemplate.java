package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Student;

import java.util.UUID;

public class StudentTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public enum TYPES {
        STUDENT,
        STUDENT_WITH_ID
    }

    @Override
    public void load() {
        buildStudentTemplate();
        buildStudentWithIdTemplate();
    }

    private void buildStudentTemplate() {
        Fixture.of(Student.class).addTemplate(TYPES.STUDENT.name(), new Rule() {{
            add(NAME, "Isaias Tavares da Silva Neto");
            add(EMAIL, "isaias_neto@discente.ufg.br");
            add(PASSWORD, "1e9f258f838afe310eb0da6501e3c354");
        }});
    }

    private void buildStudentWithIdTemplate() {
        Fixture.of(Student.class).addTemplate(TYPES.STUDENT_WITH_ID.name()).inherits(TYPES.STUDENT.name(), new Rule(){{
            add(ID, UUID.randomUUID());
        }});
    }

}
