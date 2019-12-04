package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.listelab.ColunaDTO;

public class ColunaDTOTemplate implements TemplateLoader {

    private static final String LETRA = "letra";
    private static final String DESCRICAO = "descricao";

    public enum TYPES {
        COLUMN_PRINCIPAL_A,
        COLUMN_ASSOCIATE_A,
        COLUMN_PRINCIPAL_B,
        COLUMN_ASSOCIATE_B
    }

    @Override
    public void load() {
        buildColumnPrincipalATemplate();
        buildColumnAssociateATemplate();
        buildColumnPrincipalBTemplate();
        buildColumnAssociateBTemplate();
    }

    private void buildColumnPrincipalATemplate() {
        Fixture.of(ColunaDTO.class).addTemplate(TYPES.COLUMN_PRINCIPAL_A.name(), new Rule() {{
            add(LETRA, "A");
            add(DESCRICAO, "Azul");
        }});
    }

    private void buildColumnAssociateATemplate() {
        Fixture.of(ColunaDTO.class).addTemplate(TYPES.COLUMN_ASSOCIATE_A.name(), new Rule() {{
            add(LETRA, "A");
            add(DESCRICAO, "Cor");
        }});
    }

    private void buildColumnPrincipalBTemplate() {
        Fixture.of(ColunaDTO.class).addTemplate(TYPES.COLUMN_PRINCIPAL_B.name(), new Rule() {{
            add(LETRA, "B");
            add(DESCRICAO, "Maçã");
        }});
    }

    private void buildColumnAssociateBTemplate() {
        Fixture.of(ColunaDTO.class).addTemplate(TYPES.COLUMN_ASSOCIATE_B.name(), new Rule() {{
            add(LETRA, "B");
            add(DESCRICAO, "Fruta");
        }});
    }

}
