package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;

public class AreaDoConhecimentoDTOTemplate implements TemplateLoader {

    private static final String CODIGO = "codigo";
    private static final String DESCRICAO = "descricao";

    public enum TYPES {
        ENGENHARIA_DE_SOFTWARE
    }

    @Override
    public void load() {
        buildEngenhariaDeSoftwareTemplate();
    }

    private void buildEngenhariaDeSoftwareTemplate() {
        Fixture.of(AreaDoConhecimentoDTO.class).addTemplate(TYPES.ENGENHARIA_DE_SOFTWARE.name(), new Rule() {{
            add(CODIGO, "10303022");
            add(DESCRICAO, "ENGENHARIA DE SOFTWARE");
        }});
    }

}
