package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.template.ListIntegrationDTOTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListConverterDTOTest extends BaseTest {

    @Test
    public void testFromListIntegrationToListsDTO() {
        // Setup
        final ListIntegrationDTO listIntegrationDTO = Fixture.from(ListIntegrationDTO.class).gimme(ListIntegrationDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        // Run the test
        final ListDTO result = ListConverterDTO.fromListIntegrationToListDTO(listIntegrationDTO);

        // Verify the results
        assertEquals(listIntegrationDTO.getId(), result.getId());
        assertEquals(listIntegrationDTO.getTitulo(), result.getName());
        assertEquals(listIntegrationDTO.getUsuario(), result.getUser());
        assertEquals(listIntegrationDTO.getQuestoes().size(), result.getQuestions().size());
        assertEquals(2, result.getDifficultyLevel());
        assertEquals(1, result.getSubjects().size());
        assertEquals(2, result.getTags().size());
        assertEquals(1, result.getKnowledgeAreas().size());
    }

}
