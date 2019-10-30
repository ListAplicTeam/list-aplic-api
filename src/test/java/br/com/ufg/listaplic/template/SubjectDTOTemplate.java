package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.dto.SubjectType;

public class SubjectDTOTemplate implements TemplateLoader {

    private static final String NAME = "name";
    private static final String CODE = "code";

    public enum TYPES {
        SUBJECT_INF0233,
    }

    @Override
    public void load() {
        buildSubjectInf0233Template();
    }

    private void buildSubjectInf0233Template() {
        Fixture.of(SubjectDTO.class).addTemplate(TYPES.SUBJECT_INF0233.name(), new Rule() {{
            add(NAME, SubjectType.INF0233.getName());
            add(CODE, SubjectType.INF0233.getCode());
        }});
    }

}
