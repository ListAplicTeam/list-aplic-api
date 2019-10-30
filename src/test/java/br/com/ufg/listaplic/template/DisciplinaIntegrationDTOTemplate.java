package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;

public class DisciplinaIntegrationDTOTemplate implements TemplateLoader {

    private static final String CODIGO = "codigo";
    private static final String DESCRICAO = "descricao";

    public enum TYPES {
        PRATICA_ENGENHARIA_DE_SOFTWARE
    }

    @Override
    public void load() {
        buildPraticaEngenhariaDeSoftwareTemplate();
    }

    private void buildPraticaEngenhariaDeSoftwareTemplate() {
        Fixture.of(DisciplinaIntegrationDTO.class).addTemplate(TYPES.PRATICA_ENGENHARIA_DE_SOFTWARE.name(), new Rule() {{
            add(CODIGO, "INF0150");
            add(DESCRICAO, "Prática em Engenharia de Software");
        }});
    }

}
