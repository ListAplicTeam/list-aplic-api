package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.exception.NoOneStudentOnClassroomException;
import br.com.ufg.listaplic.model.*;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.*;
import br.com.ufg.listaplic.template.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ListApplicationServiceTest extends BaseTest {

	@InjectMocks
	private ListApplicationService listApplicationServiceUnderTest;

	@Mock
	private ListApplicationJpaRepository mockListApplicationJpaRepository;
	@Mock
	private ClassroomJpaRepository mockClassroomJpaRepository;
	@Mock
	private StudentJpaRepository mockStudentJpaRepository;
	@Mock
	private AnswerJpaRepository mockAnswerJpaRepository;
	@Mock
	private ClassroomService classroomService;
	@Mock
	private QuestionCountJpaRepository questionCountJpaRepository;
	@Mock
	private ListElabNetwork listElabNetwork;

	@Test
	public void testGetFinishedListsByClassroomId() {
		// Setup
		final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
		final List<Student> students = Fixture.from(Student.class).gimme(1, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
		final List<ListApplication> applications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
		when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(classroom));
		when(mockListApplicationJpaRepository.findByClassroomAndStatus(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(applications);
		when(mockStudentJpaRepository.findStudentsByClassroomId(any(UUID.class))).thenReturn(students);
		when(listElabNetwork.getListById(any(UUID.class))).thenReturn(new ListDTO());

		// Run the Test
		final List<ListApplicationDTO> result = listApplicationServiceUnderTest.getListsByClassroom(classroom.getId(), ApplicationListStatus.ENCERRADA);

		// Verify the results
		assertEquals(applications.size(), result.size());
	}

	@Test
	public void testGetListApplicationDetail() {
		// Setup
		ListApplication application = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
		List<Answer> answers = Fixture.from(Answer.class).gimme(2, AnswerTemplate.TYPES.ANSWER.name());
		List<Student> students = Fixture.from(Student.class).gimme(1, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
		when(mockListApplicationJpaRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(application));
		when(mockAnswerJpaRepository.findByApplicationId(any(UUID.class))).thenReturn(answers);
		when(mockStudentJpaRepository.findStudentsByClassroomId(any(UUID.class))).thenReturn(students);
		when(listElabNetwork.getListById(any(UUID.class))).thenReturn(new ListDTO());

		// Run the Test
		final ListApplicationDTO result = listApplicationServiceUnderTest.getListApplicationDetail(application.getId());

		// Verify the result
		assertEquals(application.getId(), result.getId());
		assertEquals(application.getList(), result.getListId());
		assertEquals(application.getClassroom().getId(), result.getGroupId());
		assertEquals(answers.size(), result.getAnswerList().size());
	}

	@Test
	public void testApplyListTo() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
		final Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(classroomDTO);
		when(classroomService.findEnrollments(any())).thenReturn(buildEnrollmentSet(classroom));

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(mockListApplicationJpaRepository, times(1)).save(any());
	}

	@Test(expected = NullPointerException.class)
	public void testApplyListToWithoutClassroom() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());

		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(null);

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(applyDto.getClassroomId());

	}

	@Test(expected = NoOneStudentOnClassroomException.class)
	public void testApplyListToClassroomWithoutStudent() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(classroomDTO);

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(applyDto.getClassroomId());
	}

	@Test
	public void testSaveListApplication() {
		// Setup
		final ListApplication listApplication = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.LIST_APPLICATION.name());

		when(mockListApplicationJpaRepository.save(any())).thenReturn(listApplication);

		// Run the test
		ListApplication result = listApplicationServiceUnderTest.save(listApplication);

		// Verify the results
		verify(mockListApplicationJpaRepository, times(1)).save(any());

		assertEquals(listApplication.getId(), result.getId());
		assertEquals(listApplication.getList(), result.getList());
		assertEquals(listApplication.getStatus(), result.getStatus());
		assertEquals(listApplication.getClassroom(), result.getClassroom());
		assertEquals(listApplication.getClass(), result.getClass());
		assertEquals(listApplication.getFinalDate(), result.getFinalDate());
		assertEquals(listApplication.getStartDate(), result.getStartDate());
		assertEquals(listApplication.getApplicationDateTime(), result.getApplicationDateTime());
	}

	private Set<Enrollment> buildEnrollmentSet(Classroom classroom) {
		Set<Enrollment> enrollments = new HashSet<>();
		Enrollment enrollment = new Enrollment();

		enrollment.setClassroom(classroom);
		enrollments.add(enrollment);

		return enrollments;
	}

}
