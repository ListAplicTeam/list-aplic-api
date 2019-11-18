package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.model.Role;

import java.util.UUID;

public class UserDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";

    public enum TYPES {
        USER
    }

    @Override
    public void load() {
        buildUserTemplate();
    }

    private void buildUserTemplate() {
        Fixture.of(UserDTO.class).addTemplate(TYPES.USER.name(), new Rule() {{
            add(ID, UUID.randomUUID().toString());
            add(NAME, "Isaias Tavares da Silva Neto");
            add(EMAIL, "isaias_neto@discente.ufg.br");
            add(ROLE, Role.DISCENTE);
        }});
    }

}
