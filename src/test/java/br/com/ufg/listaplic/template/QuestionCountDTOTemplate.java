package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.QuestionCountDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;

public class QuestionCountDTOTemplate implements TemplateLoader {

    private static final String QUESTION = "question";
    private static final String COUNTER = "counter";

    public enum TYPES {
        QUESTION_COUNT_DTO
    }

    @Override
    public void load() {
        buildQuestionCountDTO();
    }

    private void buildQuestionCountDTO() {
        Fixture.of(QuestionCountDTO.class).addTemplate(TYPES.QUESTION_COUNT_DTO.name(), new Rule() {{
            add(QUESTION, one(QuestionDTO.class, QuestionDTOTemplate.TYPES.QUESTION.name()));
            add(COUNTER, (int)Math.floor(Math.random() * 6 + 1));
        }});
    }
}
