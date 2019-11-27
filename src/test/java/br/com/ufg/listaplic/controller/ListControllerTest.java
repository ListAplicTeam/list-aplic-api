package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.service.ListApplicationService;
import br.com.ufg.listaplic.service.ListService;
import br.com.ufg.listaplic.template.ApplyDTOTemplate;
import br.com.ufg.listaplic.template.ListApplicationDTOTemplate;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListControllerTest extends BaseTest {

	@InjectMocks
	private ListController listControllerUnderTest;

	@Mock
	private ListApplicationService mockListApplicationService;

	@Mock
	private ListService mockListService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testGetListsByFilter() {
		// Setup
		final List<ListDTO> listDTOS = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());
		when(mockListService.getListsByFilter(anyString(), anyInt(), anyString(), anyInt(), anyList())).thenReturn(listDTOS);

		// Run the test
		final List<ListDTO> result = listControllerUnderTest.getListsByFilter(anyString(), anyInt(), anyString(), anyInt(), anyList());

		// Verify the results
		assertEquals(listDTOS.size(), result.size());
	}

	@Test
	public void testGetFinishedApplicationsByClassroomId() {
		// Setup
		final List<ListApplicationDTO> listApplicationDTOS = Fixture.from(ListApplicationDTO.class)
				.gimme(2, ListApplicationDTOTemplate.TYPES.APPLICATIONDTO_WITH_ANSWERS.name());
		final ApplicationListStatus status = ApplicationListStatus.ENCERRADA;

		when(mockListApplicationService.getListsByClassroom(any(UUID.class), eq(status))).thenReturn(listApplicationDTOS);

		// Run the test
		final List<ListApplicationDTO> result = listControllerUnderTest.getApplicationsByClassroom(UUID.randomUUID(), status.name());

		// Verify the results
		assertEquals(listApplicationDTOS.size(), result.size());
	}

	@Test
	public void testGetApplicationsByClassroomId() {
		// Setup
		final List<ListApplicationDTO> listApplicationDTOS = Fixture.from(ListApplicationDTO.class)
				.gimme(2, ListApplicationDTOTemplate.TYPES.APPLICATIONDTO_WITH_ANSWERS.name());

		when(mockListApplicationService.getListsByClassroom(any(UUID.class), any())).thenReturn(listApplicationDTOS);

		// Run the test
		final List<ListApplicationDTO> result = listControllerUnderTest.getApplicationsByClassroom(UUID.randomUUID(), null);

		// Verify the results
		assertEquals(listApplicationDTOS.size(), result.size());
	}

	@Test
	public void testShouldThrowExceptionWhenGetApplicationsByClassroomIdWithInvalidStatus() {
		// Setup
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("The status param is invalid");

		// Run the test
		final List<ListApplicationDTO> result = listControllerUnderTest.getApplicationsByClassroom(UUID.randomUUID(), "STATUS_INVALIDO");

		// Verify the results
		verify(mockListApplicationService, times(0)).getListsByClassroom(any(UUID.class), any());
	}

	@Test
	public void testGetApplicationDetailById() {
		final ListApplicationDTO listApplicationDTO = Fixture.from(ListApplicationDTO.class)
				.gimme(ListApplicationDTOTemplate.TYPES.APPLICATIONDTO_WITH_ANSWERS.name());
		when(mockListApplicationService.getListApplicationDetail(any(UUID.class))).thenReturn(listApplicationDTO);

		final ListApplicationDTO result = listControllerUnderTest.getApplicationDetailById(UUID.randomUUID());

		assertEquals(listApplicationDTO.getListId(), result.getListId());
		assertEquals(listApplicationDTO.getGroupId(), result.getGroupId());
		assertEquals(listApplicationDTO.getStatus(), result.getStatus());
	}

	@Test
	public void testGetPendingListsByStudent() {
		// Setup
		final List<ListDTO> listDTOS = Fixture.from(ListDTO.class).gimme(2, ListDTOTemplate.TYPES.LIST_WITH_TWO_QUESTION.name());
		when(mockListService.getPendingListsByStudent(any(UUID.class))).thenReturn(listDTOS);

		// Run the test
		final List<ListDTO> result = listControllerUnderTest.getPendingListsByStudent(UUID.randomUUID());

		// Verify the results
		assertEquals(listDTOS.size(), result.size());
	}

    @Test
    public void testAnsweringList() {
        // Setup
        Mockito.doNothing().when(mockListService).answeringList(any(), any(UUID.class), any(ListDTO.class));

        // Run the test
        listControllerUnderTest.answeringList(AnswerStatusType.SAVE, UUID.randomUUID(), new ListDTO());

        // Verify the results
        verify(mockListService, times(1)).answeringList(any(), any(UUID.class), any(ListDTO.class));
    }

	@Test
	public void testApplyListToGroup() {
		// Setup
		final ApplyDTO applyDTO = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		Mockito.doNothing().when(mockListApplicationService).applyListTo(any(ApplyDTO.class));

		// Run the test
		ResponseEntity responseEntity = listControllerUnderTest.applyListToGroup(applyDTO);

		// Verify the results
		verify(mockListApplicationService, times(1)).applyListTo(any(ApplyDTO.class));

		assertEquals(ResponseEntity.ok().build(), responseEntity);
	}

}
