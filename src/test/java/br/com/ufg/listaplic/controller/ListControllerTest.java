package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.service.ListService;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ListControllerTest extends BaseTest {

    @InjectMocks
    private ListController listControllerUnderTest;

    @Mock
    private ListService mockListService;

    @Test
    public void testFindAll() {
        // Setup
        final List<ListDTO> listDTOS = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());
        when(mockListService.findListByInstructor("professor@ufg.br", null, null, false)).thenReturn(listDTOS);

        // Run the test
        final List<ListDTO> result = listControllerUnderTest.findList("professor@ufg.br", null, null, false);

        // Verify the results
        assertEquals(listDTOS.size(), result.size());
    }

}
