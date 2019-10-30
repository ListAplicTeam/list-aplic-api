package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;

import java.util.UUID;

public class ListDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String LIST_APPLICATION_ID = "listApplicationId";
    private static final String NAME = "name";
    private static final String USER = "user";
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String QUESTIONS = "questions";

    public enum TYPES {
        LIST_WITH_ONE_QUESTION,
        LIST_WITH_TWO_QUESTION
    }

    @Override
    public void load() {
        buildListWithOneQuestionTemplate();
        buildListWithTwoQuestionTemplate();
    }

    private void buildListWithOneQuestionTemplate() {
        Fixture.of(ListDTO.class).addTemplate(TYPES.LIST_WITH_ONE_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(LIST_APPLICATION_ID, UUID.randomUUID());
            add(NAME, "Lista de Teste com uma questão");
            add(USER, "professor@ufg.br");
            add(SUBJECT_CODE, "INF0150");
            add(QUESTIONS, has(1).of(QuestionDTO.class, QuestionDTOTemplate.TYPES.QUESTION.name()));
        }});
    }

    private void buildListWithTwoQuestionTemplate() {
        Fixture.of(ListDTO.class).addTemplate(TYPES.LIST_WITH_TWO_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(LIST_APPLICATION_ID, UUID.randomUUID());
            add(NAME, "Lista de Teste com duas questões");
            add(USER, "professor@ufg.br");
            add(SUBJECT_CODE, "INF0233");
            add(QUESTIONS, has(2).of(QuestionDTO.class, QuestionDTOTemplate.TYPES.QUESTION.name()));
        }});
    }

}
