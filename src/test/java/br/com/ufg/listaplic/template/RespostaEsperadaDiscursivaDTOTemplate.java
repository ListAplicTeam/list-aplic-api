package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaDiscursivaDTO;

public class RespostaEsperadaDiscursivaDTOTemplate implements TemplateLoader {

    private static final String PESO = "peso";
    private static final String DESCRICAO = "descricao";

    public enum TYPES {
        EXPECTED_ANSWER,
    }

    @Override
    public void load() {
        buildExpectedAnswerTemplate();
    }

    private void buildExpectedAnswerTemplate() {
        Fixture.of(RespostaEsperadaDiscursivaDTO.class).addTemplate(TYPES.EXPECTED_ANSWER.name(), new Rule() {{
            add(PESO, 3);
            add(DESCRICAO, "Questão Discursiva");
        }});
    }

}
