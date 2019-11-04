package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.AnswerDTO;

import java.util.UUID;

public class AnswerDTOTemplate implements TemplateLoader {

    private static final String LIST_ID = "listId";
    private static final String QUESTION_ID = "questionId";
    private static final String ANSWER = "answer";

    public enum TYPES {
        ANSWER_DTO
    }

    @Override
    public void load() {
        buildAnswerTemplate();
    }

    private void buildAnswerTemplate() {
        Fixture.of(AnswerDTO.class).addTemplate(TYPES.ANSWER_DTO.name(), new Rule() {{
            add(LIST_ID, UUID.randomUUID());
            add(QUESTION_ID, UUID.randomUUID());
            add(ANSWER, "Lenda");
        }});
    }

}
