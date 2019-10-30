package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListServiceTest extends BaseTest {

    @InjectMocks
    private ListService listServiceUnderTest;

    @Mock
    private ListElabNetwork mockListElabNetwork;

    @Test
    public void testGetAllLists() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getLists()).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter(null, null);

        // Verify the results
        verify(mockListElabNetwork, times(1)).getLists();

        assertEquals(lists.size(), result.size());
    }

    @Test
    public void testGetListsByName() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getLists()).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter("Duas Questões", null);

        // Verify the results
        verify(mockListElabNetwork, times(1)).getLists();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetListsBySujectCode() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getLists()).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter(null, "INF0150");

        // Verify the results
        verify(mockListElabNetwork, times(1)).getLists();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetListsByNameAndSujectCode() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getLists()).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter("Duas Questões", "INF0233");

        // Verify the results
        verify(mockListElabNetwork, times(1)).getLists();

        assertEquals(1, result.size());
    }

}
