package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.InstructorDTO;

import java.util.UUID;

public class InstructorDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public enum TYPES {
        INSTRUCTOR
    }

    @Override
    public void load() {
        buildInstructorTemplate();
    }

    private void buildInstructorTemplate() {
        Fixture.of(InstructorDTO.class).addTemplate(TYPES.INSTRUCTOR.name(), new Rule() {{
            add(ID, UUID.fromString("91b4a2dd-1797-48bb-8353-1231888129a2"));
            add(NAME, "Rubens Ferreira");
            add(EMAIL, "isaias_neto@ufg.br");
        }});
    }

}
