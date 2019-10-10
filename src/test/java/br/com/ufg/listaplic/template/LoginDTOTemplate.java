package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.LoginDTO;

public class LoginDTOTemplate implements TemplateLoader {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public enum TYPES {
        LOGIN_DTO,
    }

    @Override
    public void load() {
        buildLoginDTOTemplate();
    }

    private void buildLoginDTOTemplate() {
        Fixture.of(LoginDTO.class).addTemplate(TYPES.LOGIN_DTO.name(), new Rule() {{
            add(EMAIL, "isaias_neto@discente.ufg.br");
            add(PASSWORD, "nobodyyesdoor");
        }});
    }

}
