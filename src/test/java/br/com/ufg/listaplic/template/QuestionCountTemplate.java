package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.QuestionCount;

import java.util.UUID;

public class QuestionCountTemplate implements TemplateLoader {

    private static final String QUESTION = "question";
    private static final String INSTRUCTOR = "instructor";
    private static final String COUNTER = "counter";

    public enum TYPES {
        QUESTION_COUNT
    }

    @Override
    public void load() {
        buildQuestionCount();
    }

    private void buildQuestionCount() {
        Fixture.of(QuestionCount.class).addTemplate(TYPES.QUESTION_COUNT.name(), new Rule() {{
            add(QUESTION, UUID.randomUUID());
            add(INSTRUCTOR, UUID.randomUUID().toString());
            add(COUNTER, (int)Math.floor(Math.random() * 6 + 1));
        }});
    }
}
