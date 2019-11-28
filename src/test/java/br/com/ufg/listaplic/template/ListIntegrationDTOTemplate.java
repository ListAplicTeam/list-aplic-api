package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestoesIntegrationDTO;

import java.util.Collections;
import java.util.UUID;

public class ListIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String USUARIO = "usuario";
    private static final String PRONTA_PARA_APLICACAO = "prontaParaAplicacao";
    private static final String QUESTOES_DISCURSIVA = "questoesDiscursiva";
    private static final String QUESTOES_MULTIPLA_ESCOLHA = "questoesMultiplaEscolha";
    private static final String QUESTOES_ASSOCIACAO_DE_COLUNAS = "questoesAssociacaoDeColunas";
    private static final String QUESTOES_VERDADEIRO_OU_FALSO = "questoesVerdadeiroOuFalso";

    public enum TYPES {
        LIST_WITH_ONE_QUESTION,
        LIST_WITH_TWO_QUESTION,
        LIST_WITH_FOUR_QUESTION
    }

    @Override
    public void load() {
        buildListWithOneQuestionTemplate();
        buildListWithTwoQuestionTemplate();
        buildListWithFourQuestionTemplate();
    }

    private void buildListWithOneQuestionTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST_WITH_ONE_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(PRONTA_PARA_APLICACAO, true);
            add(QUESTOES_DISCURSIVA, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.DISCURSIVE.name()));
            add(QUESTOES_MULTIPLA_ESCOLHA, Collections.emptyList());
            add(QUESTOES_ASSOCIACAO_DE_COLUNAS, Collections.emptyList());
            add(QUESTOES_VERDADEIRO_OU_FALSO, Collections.emptyList());
        }});
    }

    private void buildListWithTwoQuestionTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST_WITH_TWO_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(PRONTA_PARA_APLICACAO, true);
            add(QUESTOES_DISCURSIVA, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.DISCURSIVE.name()));
            add(QUESTOES_MULTIPLA_ESCOLHA, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.MULTIPLE_CHOICE.name()));
            add(QUESTOES_ASSOCIACAO_DE_COLUNAS, Collections.emptyList());
            add(QUESTOES_VERDADEIRO_OU_FALSO, Collections.emptyList());
        }});
    }

    private void buildListWithFourQuestionTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST_WITH_FOUR_QUESTION.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(PRONTA_PARA_APLICACAO, true);
            add(QUESTOES_DISCURSIVA, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.DISCURSIVE.name()));
            add(QUESTOES_MULTIPLA_ESCOLHA, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.MULTIPLE_CHOICE.name()));
            add(QUESTOES_ASSOCIACAO_DE_COLUNAS, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.COLUMN_BINDING.name()));
            add(QUESTOES_VERDADEIRO_OU_FALSO, has(1).of(QuestoesIntegrationDTO.class, QuestoesIntegrationDTOTemplate.TYPES.TRUE_OR_FALSE.name()));
        }});
    }

}
