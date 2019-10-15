package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.EnrollmentDTO;

public class EnrollmentDTOTemplate implements TemplateLoader {

    private static final String CODE = "code";

    public enum TYPES {
        ENROLLMENT
    }

    @Override
    public void load() {
        buildEnrollmentDTOTemplate();
    }

    private void buildEnrollmentDTOTemplate() {
        Fixture.of(EnrollmentDTO.class).addTemplate(TYPES.ENROLLMENT.name(), new Rule() {{
            add(CODE,  "FLA0214");
        }});
    }

}
