package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaMultiplaEscolhaDTO;

public class RespostaEsperadaMultiplaEscolhaDTOTemplate implements TemplateLoader {

    private static final String DESCRICAO = "descricao";
    private static final String CORRETA = "correta";

    public enum TYPES {
        EXPECTED_ANSWER,
    }

    @Override
    public void load() {
        buildExpectedAnswerTemplate();
    }

    private void buildExpectedAnswerTemplate() {
        Fixture.of(RespostaEsperadaMultiplaEscolhaDTO.class).addTemplate(TYPES.EXPECTED_ANSWER.name(), new Rule() {{
            add(DESCRICAO, "Questão Discursiva");
            add(CORRETA, true);
        }});
    }

}
