package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.ObjetivasIntegrationDTO;

import java.util.UUID;

public class ObjetivasIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String ENUNCIADO = "enunciado";

    public enum TYPES {
        QUESTION_1
    }

    @Override
    public void load() {
        buildQuestion1Template();
    }

    private void buildQuestion1Template() {
        Fixture.of(ObjetivasIntegrationDTO.class).addTemplate(TYPES.QUESTION_1.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
        }});
    }

}
