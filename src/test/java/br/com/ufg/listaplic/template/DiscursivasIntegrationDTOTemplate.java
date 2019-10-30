package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.DiscursivasIntegrationDTO;

import java.util.Arrays;
import java.util.UUID;

public class DiscursivasIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String ENUNCIADO = "enunciado";
    private static final String USUARIO = "usuario";
    private static final String AREA_DE_CONHECIMENTO = "areaDeConhecimento";
    private static final String NIVEL_DIFICULDADE = "nivelDificuldade";
    private static final String DISCIPLINA = "disciplina";
    private static final String TAGS = "tags";
    private static final String TIPO = "tipo";
    private static final String TEMPO_MAXIMO_DE_RESPOSTA = "tempoMaximoDeResposta";

    public enum TYPES {
        QUESTION_1
    }

    @Override
    public void load() {
        buildQuestion1Template();
    }

    private void buildQuestion1Template() {
        Fixture.of(DiscursivasIntegrationDTO.class).addTemplate(TYPES.QUESTION_1.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(ENUNCIADO, "Qual dos fatores de qualidade de um software, avalia a capacidade de o sistema funcionar mesmo em condições anormais?");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, null);
            add(NIVEL_DIFICULDADE, 1);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Arrays.asList("ENGSOFT"));
            add(TIPO, 0);
            add(TEMPO_MAXIMO_DE_RESPOSTA, 3);
        }});
    }

}
