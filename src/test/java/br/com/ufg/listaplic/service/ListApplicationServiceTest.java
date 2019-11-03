package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.template.ApplyDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class ListApplicationServiceTest extends BaseTest {

	@InjectMocks
	private ListApplicationService listApplicationService;

	@Mock
	private ClassroomService classroomService;

	@Mock
	private ListElabNetwork mockListElabNetwork;

	@Mock
	private ListApplicationJpaRepository listApplicationJpaRepository;

	@Test
	public void testApplyListTo() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		final ListDTO list = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name());
		final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

		when(mockListElabNetwork.getListById(applyDto.getListId())).thenReturn(list);
		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(classroomDTO);

		// Run the test
		listApplicationService.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(applyDto.getClassroomId());
		verify(mockListElabNetwork, times(1)).getListById(applyDto.getListId());

	}

	@Test(expected = NullPointerException.class)
	public void testApplyListToWithoutClassroom() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());

		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(null);

		// Run the test
		listApplicationService.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(applyDto.getClassroomId());

	}

	@Test(expected = NullPointerException.class)
	public void testApplyListToWithoutList() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		final ListDTO list = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name());
		final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

		when(mockListElabNetwork.getListById(applyDto.getListId())).thenReturn(null);
		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(classroomDTO);

		// Run the test
		listApplicationService.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(applyDto.getClassroomId());
		verify(mockListElabNetwork, times(1)).getListById(applyDto.getListId());

	}

}
