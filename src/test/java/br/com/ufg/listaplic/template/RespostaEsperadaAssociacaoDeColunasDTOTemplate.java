package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.ColunaDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaAssociacaoDeColunasDTO;

public class RespostaEsperadaAssociacaoDeColunasDTOTemplate implements TemplateLoader {

    private static final String COLUNA_PRINCIPAL = "colunaPrincipal";
    private static final String COLUNA_ASSOCIADA = "colunaAssociada";

    public enum TYPES {
        EXPECTED_ANSWER_A,
        EXPECTED_ANSWER_B,
    }

    @Override
    public void load() {
        buildExpectedAnswerATemplate();
        buildExpectedAnswerBTemplate();
    }

    private void buildExpectedAnswerATemplate() {
        Fixture.of(RespostaEsperadaAssociacaoDeColunasDTO.class).addTemplate(TYPES.EXPECTED_ANSWER_A.name(), new Rule() {{
            add(COLUNA_PRINCIPAL, one(ColunaDTO.class, ColunaDTOTemplate.TYPES.COLUMN_PRINCIPAL_A.name()));
            add(COLUNA_ASSOCIADA, one(ColunaDTO.class, ColunaDTOTemplate.TYPES.COLUMN_PRINCIPAL_A.name()));
        }});
    }

    private void buildExpectedAnswerBTemplate() {
        Fixture.of(RespostaEsperadaAssociacaoDeColunasDTO.class).addTemplate(TYPES.EXPECTED_ANSWER_B.name(), new Rule() {{
            add(COLUNA_PRINCIPAL, one(ColunaDTO.class, ColunaDTOTemplate.TYPES.COLUMN_PRINCIPAL_B.name()));
            add(COLUNA_ASSOCIADA, one(ColunaDTO.class, ColunaDTOTemplate.TYPES.COLUMN_PRINCIPAL_B.name()));
        }});
    }

}
