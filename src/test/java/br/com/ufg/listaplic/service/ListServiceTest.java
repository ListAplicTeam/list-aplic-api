package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ListApplicationTemplate;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ListServiceTest extends BaseTest {

    @InjectMocks
    private ListService listServiceUnderTest;

    @Mock
    private ListElabNetwork mockListElabNetwork;

    @Mock
    private ClassroomService mockClassroomService;

    @Mock
    private ListApplicationJpaRepository mockListApplicationJpaRepository;

    @Mock
    private AnswerService mockAnswerService;

    @Test
    public void testGetAllLists() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

		when(mockListElabNetwork.getListsByFilter(any())).thenReturn(lists);

        // Run the test
		List<ListDTO> result = listServiceUnderTest.getListsByFilter("Lista", "INF0233", anyString(), anyInt(), anyList());

        // Verify the results
		verify(mockListElabNetwork, times(1)).getListsByFilter(any());

        assertEquals(lists.size(), result.size());
    }

    @Test
    public void testGetListsByName() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

		when(mockListElabNetwork.getListsByFilter(any())).thenReturn(lists);

        // Run the test
		List<ListDTO> result = listServiceUnderTest.getListsByFilter("Lista", "INF0233", anyString(), anyInt(), anyList());

        // Verify the results
		verify(mockListElabNetwork, times(1)).getListsByFilter(any());

        assertEquals(1, result.size());
    }

    @Test
    public void testGetListsByNameAndSujectCode() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

		when(mockListElabNetwork.getListsByFilter(any())).thenReturn(lists);

        // Run the test
		List<ListDTO> result = listServiceUnderTest.getListsByFilter("Lista", "INF0233", anyString(), anyInt(), anyList());

        // Verify the results
		verify(mockListElabNetwork, times(1)).getListsByFilter(any());

        assertEquals(1, result.size());
    }

    @Test
    public void testGetPendingListsByStudentShouldReturnEmptyList() {
        // Setup
        when(mockClassroomService.findByStudentId(any(UUID.class))).thenReturn(Collections.emptyList());

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findByStudentId(any(UUID.class));

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetPendingListsByStudent() {
        // Setup
        final List<ClassroomDTO> classrooms = Fixture.from(ClassroomDTO.class).gimme(2, ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final List<ListApplication> listApplications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.LIST_APPLICATION.name());
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockClassroomService.findByStudentId(any(UUID.class))).thenReturn(classrooms);
        when(mockListApplicationJpaRepository.findByClassrooms(anyList(), any(UUID.class))).thenReturn(listApplications);
        when(mockListElabNetwork.getListById(any(UUID.class))).thenReturn(listDTO);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findByStudentId(any(UUID.class));
        verify(mockListApplicationJpaRepository, times(1)).findByClassrooms(anyList(), any(UUID.class));
        verify(mockListElabNetwork, times(2)).getListById(any(UUID.class));

        assertEquals(listApplications.size(), result.size());
    }

    @Test
    public void testAnsweringList() {
        // Setup
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        Mockito.doNothing().when(mockAnswerService).saveAll(anyList());

        // Run the test
        listServiceUnderTest.answeringList(UUID.randomUUID(), listDTO);

        // Verify the results
        verify(mockAnswerService, times(1)).saveAll(anyList());
    }

}
