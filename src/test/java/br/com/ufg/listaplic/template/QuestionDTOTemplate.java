package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;

import java.util.Collections;
import java.util.UUID;

public class QuestionDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String OPTIONS = "options";
    private static final String ANSWER = "answer";

    public enum TYPES {
        QUESTION,
    }

    @Override
    public void load() {
        buildQuestionTemplate();
    }

    private void buildQuestionTemplate() {
        Fixture.of(QuestionDTO.class).addTemplate(TYPES.QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(NAME,"O que a palavra 'legend' significa em português?");
            add(TYPE, QuestionType.DISCURSIVE);
            add(OPTIONS, Collections.emptyList());
            add(ANSWER, "Lenda");
        }});
    }

}
