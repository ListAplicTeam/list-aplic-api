package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.UserIntegrationDTO;

public class UserIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String TOKEN = "token";
    private static final String ROLE = "role";

    public enum TYPES {
        USER
    }

    @Override
    public void load() {
        buildUserTemplate();
    }

    private void buildUserTemplate() {
        Fixture.of(UserIntegrationDTO.class).addTemplate(TYPES.USER.name(), new Rule() {{
            add(ID, "5da3453a5718e904108acc25");
            add(EMAIL, "professor@ufg.br");
            add(PASSWORD, "123456");
            add(NAME, "Professor");
            add(TOKEN, "1f384e4760b38e92bbbdc0f726d5ba486b090316cd1d1bc0b69a3a9b3d3d2bab");
            add(ROLE, 1);
        }});
    }

}
