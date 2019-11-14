package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;

import java.util.UUID;

public class ListIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String USUARIO = "usuario";
    private static final String PRONTA_PARA_APLICACAO = "prontaParaAplicacao";
    private static final String QUESTOES = "questoes";

    public enum TYPES {
        LIST_WITH_ONE_QUESTION,
        LIST_WITH_TWO_QUESTION
    }

    @Override
    public void load() {
        buildListWithOneQuestionTemplate();
        buildListWithTwoTemplate();
    }

    private void buildListWithOneQuestionTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST_WITH_ONE_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(PRONTA_PARA_APLICACAO, true);
            add(QUESTOES, has(1).of(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.QUESTION_1.name()));
        }});
    }

    private void buildListWithTwoTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST_WITH_TWO_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(PRONTA_PARA_APLICACAO, true);
            add(QUESTOES, has(2).of(QuestaoIntegrationDTO.class, QuestaoIntegrationDTOTemplate.TYPES.QUESTION_1.name()));
        }});
    }

}
