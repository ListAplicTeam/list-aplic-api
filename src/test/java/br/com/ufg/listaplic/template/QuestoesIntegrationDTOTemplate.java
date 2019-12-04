package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestoesIntegrationDTO;

import java.util.UUID;

public class QuestoesIntegrationDTOTemplate implements TemplateLoader {

    private static final String NUMERO = "numero";
    private static final String PESO = "peso";
    private static final String QUESTAO = "questao";

    public enum TYPES {
        DISCURSIVE,
        MULTIPLE_CHOICE,
        TRUE_OR_FALSE,
        COLUMN_BINDING,
    }

    @Override
    public void load() {
        buildQuestionDiscursiveTemplate();
        buildQuestionMultipleChoiceTemplate();
        buildQuestionTrueOrFalseTemplate();
        buildQuestionColumnBindingTemplate();
    }

    private void buildQuestionDiscursiveTemplate() {
        Fixture.of(QuestoesIntegrationDTO.class).addTemplate(TYPES.DISCURSIVE.name(), new Rule() {{
            add(NUMERO, 1);
            add(PESO, 3);
            add(QUESTAO, one(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.DISCURSIVE.name()));
        }});
    }

    private void buildQuestionMultipleChoiceTemplate() {
        Fixture.of(QuestoesIntegrationDTO.class).addTemplate(TYPES.MULTIPLE_CHOICE.name(), new Rule() {{
            add(NUMERO, 1);
            add(PESO, 3);
            add(QUESTAO, one(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.MULTIPLE_CHOICE.name()));
        }});
    }

    private void buildQuestionTrueOrFalseTemplate() {
        Fixture.of(QuestoesIntegrationDTO.class).addTemplate(TYPES.TRUE_OR_FALSE.name(), new Rule() {{
            add(NUMERO, 1);
            add(PESO, 3);
            add(QUESTAO, one(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.TRUE_OR_FALSE.name()));
        }});
    }

    private void buildQuestionColumnBindingTemplate() {
        Fixture.of(QuestoesIntegrationDTO.class).addTemplate(TYPES.COLUMN_BINDING.name(), new Rule() {{
            add(NUMERO, 1);
            add(PESO, 3);
            add(QUESTAO, one(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.COLUMN_BINDING.name()));
        }});
    }

}
