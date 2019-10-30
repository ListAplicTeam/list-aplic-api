package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.DiscursivasIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;

import java.util.Collections;
import java.util.UUID;

public class ListIntegrationDTOTemplate implements TemplateLoader {

    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String USUARIO = "usuario";
    private static final String AREA_DE_CONHECIMENTO = "areaDeConhecimento";
    private static final String NIVEL_DIFICULDADE = "nivelDificuldade";
    private static final String TAGS = "tags";
    private static final String DISCIPLINA = "disciplina";
    private static final String DISCURSIVAS = "discursivas";
    private static final String OBJETIVAS = "objetivas";

    public enum TYPES {
        LIST
    }

    @Override
    public void load() {
        buildListTemplate();
    }

    private void buildListTemplate() {
        Fixture.of(ListIntegrationDTO.class).addTemplate(TYPES.LIST.name(), new Rule() {{
            add(ID, UUID.randomUUID());
            add(TITULO, "Lista de Teste");
            add(USUARIO, "professor@ufg.br");
            add(AREA_DE_CONHECIMENTO, null);
            add(NIVEL_DIFICULDADE, 1);
            add(DISCIPLINA, one(DisciplinaIntegrationDTO.class, DisciplinaIntegrationDTOTemplate.TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name()));
            add(TAGS, Collections.singletonList("ENGSOFT"));
            add(DISCURSIVAS, has(1).of(DiscursivasIntegrationDTO.class, DiscursivasIntegrationDTOTemplate.TYPES.QUESTION_1.name()));
            add(OBJETIVAS, null);
        }});
    }

}
