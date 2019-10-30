package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.model.Answer;

import java.util.UUID;

public class AnswerTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String LIST_ID = "listId";
    private static final String QUESTION_ID = "listId";
    private static final String USER_ID = "userId";
    private static final String ANSWER = "answer";

    public enum TYPES {
        ANSWER
    }

    @Override
    public void load() {
        buildAnswerTemplate();
    }

    private void buildAnswerTemplate() {
        Fixture.of(Answer.class).addTemplate(TYPES.ANSWER.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(LIST_ID, UUID.randomUUID());
            add(QUESTION_ID, UUID.randomUUID());
            add(USER_ID, UUID.randomUUID());
            add(ANSWER, "Lenda");
        }});
    }

}
