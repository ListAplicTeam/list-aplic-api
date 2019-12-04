package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.FilterList;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.template.AnswerTemplate;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import br.com.ufg.listaplic.template.ListApplicationTemplate;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void testGetListsByFilter() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getListsByFilter(any(FilterList.class))).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter("SUBJECT_CODE",
                5,
                "KNOWLEDGE_AREA_CODE",
                10,
                Arrays.asList("TAG_1", "TAG_2"));

        // Verify the results
        verify(mockListElabNetwork, times(1)).getListsByFilter(any(FilterList.class));

        assertEquals(lists.size(), result.size());
    }

    @Test
    public void testGetListsByName() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getListsByFilter(any())).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter("SUBJECT_CODE",
                5,
                "KNOWLEDGE_AREA_CODE",
                10,
                Arrays.asList("TAG_1", "TAG_2"));

        // Verify the results
        verify(mockListElabNetwork, times(1)).getListsByFilter(any());

        assertEquals(lists.size(), result.size());
    }

    @Test
    public void testFinishListApplication() {
        final ListApplication listApplication = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
        when(mockListApplicationJpaRepository.save(any(ListApplication.class))).thenReturn(listApplication);

        final ListApplication result = mockListApplicationJpaRepository.save(new ListApplication());

        assertEquals(result.getStatus(), listApplication.getStatus());
        assertEquals(result.getStatus(), ApplicationListStatus.ENCERRADA);
    }

    @Test
    public void testGetListsByNameAndSujectCode() {
        // Setup
        final List<ListDTO> lists = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name(), ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockListElabNetwork.getListsByFilter(any())).thenReturn(lists);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getListsByFilter("SUBJECT_CODE",
                5,
                "KNOWLEDGE_AREA_CODE",
                10,
                Arrays.asList("TAG_1", "TAG_2"));

        // Verify the results
        verify(mockListElabNetwork, times(1)).getListsByFilter(any());

        assertEquals(lists.size(), result.size());
    }

    @Test
    public void testGetPendingListsByStudentShouldReturnEmptyList() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());

        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.of(classroom));
        when(mockListApplicationJpaRepository.findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(Collections.emptyList());

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID(), UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findClassroomById(any(UUID.class));
        verify(mockListApplicationJpaRepository, times(1)).findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class));

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetPendingListsByStudent() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final List<ListApplication> listApplications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.LIST_APPLICATION.name());
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.of(classroom));
        when(mockListApplicationJpaRepository.findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(listApplications);
        when(mockListElabNetwork.getListById(any(UUID.class))).thenReturn(listDTO);

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID(), UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findClassroomById(any(UUID.class));
        verify(mockListApplicationJpaRepository, times(1)).findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class));
        verify(mockListElabNetwork, times(2)).getListById(any(UUID.class));

        assertEquals(listApplications.size(), result.size());
    }

    @Test
    public void testGetPendingListsByStudentWithAnswerDraft() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final List<ListApplication> listApplications = Fixture.from(ListApplication.class).gimme(1, ListApplicationTemplate.TYPES.LIST_APPLICATION.name());
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());
        final Answer answer = Fixture.from(Answer.class).gimme(AnswerTemplate.TYPES.ANSWER.name());

        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.of(classroom));
        when(mockListApplicationJpaRepository.findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(listApplications);
        when(mockListElabNetwork.getListById(any(UUID.class))).thenReturn(listDTO);
        when(mockAnswerService.findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(Optional.of(answer));

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID(), UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findClassroomById(any(UUID.class));
        verify(mockListApplicationJpaRepository, times(1)).findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class));
        verify(mockListElabNetwork, times(1)).getListById(any(UUID.class));
        verify(mockAnswerService, times(2)).findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class));

        assertEquals(listApplications.size(), result.size());
        assertEquals(ApplicationListStatus.EM_ANDAMENTO, result.get(0).getStatus());
    }

    @Test
    public void testGetPendingListsByStudentWithAnswerSave() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final List<ListApplication> listApplications = Fixture.from(ListApplication.class).gimme(1, ListApplicationTemplate.TYPES.LIST_APPLICATION.name());
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());
        final Answer answer = Fixture.from(Answer.class).gimme(AnswerTemplate.TYPES.ANSWER.name());
        answer.setStatusType(AnswerStatusType.SAVE);

        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.of(classroom));
        when(mockListApplicationJpaRepository.findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(listApplications);
        when(mockListElabNetwork.getListById(any(UUID.class))).thenReturn(listDTO);
        when(mockAnswerService.findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(Optional.of(answer));

        // Run the test
        List<ListDTO> result = listServiceUnderTest.getPendingListsByStudent(UUID.randomUUID(), UUID.randomUUID());

        // Verify the results
        verify(mockClassroomService, times(1)).findClassroomById(any(UUID.class));
        verify(mockListApplicationJpaRepository, times(1)).findByClassroomAndStatusNot(any(Classroom.class), any(ApplicationListStatus.class));
        verify(mockListElabNetwork, times(1)).getListById(any(UUID.class));
        verify(mockAnswerService, times(2)).findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class));

        assertEquals(listApplications.size(), result.size());
        assertEquals(ApplicationListStatus.ENCERRADA, result.get(0).getStatus());
    }

    @Test
    public void testAnsweringList() {
        // Setup
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());

        Mockito.doNothing().when(mockAnswerService).saveAll(anyList());

        // Run the test
        listServiceUnderTest.answeringList(AnswerStatusType.SAVE, UUID.randomUUID(), listDTO);

        // Verify the results
        verify(mockAnswerService, times(1)).saveAll(anyList());
    }

}
