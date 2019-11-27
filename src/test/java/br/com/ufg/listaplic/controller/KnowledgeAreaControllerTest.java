package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.template.AreaDoConhecimentoDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class KnowledgeAreaControllerTest extends BaseTest {

    @InjectMocks
    private KnowledgeAreaController knowledgeAreaControllerUnderTest;

    @Mock
    private ListElabNetwork mockListElabNetwork;

    @Test
    public void testGetListsByFilter() {
        // Setup
        final List<AreaDoConhecimentoDTO> areaDoConhecimentoDTOS = Fixture.from(AreaDoConhecimentoDTO.class).gimme(2, AreaDoConhecimentoDTOTemplate.TYPES.ENGENHARIA_DE_SOFTWARE.name());
        when(mockListElabNetwork.getAllKnowledgeAreas()).thenReturn(areaDoConhecimentoDTOS);

        // Run the test
        final List<AreaDoConhecimentoDTO> result = knowledgeAreaControllerUnderTest.findAll();

        // Verify the results
        assertEquals(areaDoConhecimentoDTOS.size(), result.size());
    }

}
