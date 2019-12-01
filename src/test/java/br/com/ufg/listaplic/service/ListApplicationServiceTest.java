package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.exception.NoOneStudentOnClassroomException;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.Enrollment;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.model.QuestionCount;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.QuestionCountJpaRepository;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import br.com.ufg.listaplic.template.AnswerTemplate;
import br.com.ufg.listaplic.template.ApplyDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import br.com.ufg.listaplic.template.ListApplicationTemplate;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import br.com.ufg.listaplic.template.QuestionDTOTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	public void testGetFindAll() {
		// Setup
		final List<ListApplication> applications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());

		when(mockListApplicationJpaRepository.findAll()).thenReturn(applications);

		// Run the Test
		final List<ListApplication> result = listApplicationServiceUnderTest.findAll();

		// Verify the results
		assertEquals(applications.size(), result.size());
	}

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
		verify(mockClassroomJpaRepository, times(1)).findById(any(UUID.class));
		verify(mockListApplicationJpaRepository, times(0)).findByClassroom(any(Classroom.class));
		verify(mockListApplicationJpaRepository, times(1)).findByClassroomAndStatus(any(Classroom.class), any(ApplicationListStatus.class));
		verify(mockStudentJpaRepository, times(1)).findStudentsByClassroomId(any(UUID.class));
		verify(listElabNetwork, times(2)).getListById(any(UUID.class));

		assertEquals(applications.size(), result.size());
	}

	@Test
	public void testGetFinishedListsByClassroomIdWithoutStatus() {
		// Setup
		final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
		final List<Student> students = Fixture.from(Student.class).gimme(1, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
		final List<ListApplication> applications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
		when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(classroom));
		when(mockListApplicationJpaRepository.findByClassroom(any(Classroom.class))).thenReturn(applications);
		when(mockStudentJpaRepository.findStudentsByClassroomId(any(UUID.class))).thenReturn(students);
		when(listElabNetwork.getListById(any(UUID.class))).thenReturn(new ListDTO());

		// Run the Test
		final List<ListApplicationDTO> result = listApplicationServiceUnderTest.getListsByClassroom(classroom.getId(), null);

		// Verify the results
		verify(mockClassroomJpaRepository, times(1)).findById(any(UUID.class));
		verify(mockListApplicationJpaRepository, times(1)).findByClassroom(any(Classroom.class));
		verify(mockListApplicationJpaRepository, times(0)).findByClassroomAndStatus(any(Classroom.class), any(ApplicationListStatus.class));
		verify(mockStudentJpaRepository, times(1)).findStudentsByClassroomId(any(UUID.class));
		verify(listElabNetwork, times(2)).getListById(any(UUID.class));

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
		final ListApplication listApplication = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.LIST_APPLICATION.name());
		final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name());
		final Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

		when(classroomService.findById(applyDto.getClassroomId())).thenReturn(classroomDTO);
		when(classroomService.findEnrollments(any())).thenReturn(buildEnrollmentSet(classroom));
		when(mockListApplicationJpaRepository.save(any())).thenReturn(listApplication);
		when(listElabNetwork.getListById(any())).thenReturn(listDTO);

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(mockListApplicationJpaRepository, times(1)).save(any());
	}

	@Test
	public void testApplyListToForSpecificGroup() {
		// Setup
		final ApplyDTO applyDto = Fixture.from(ApplyDTO.class).gimme(ApplyDTOTemplate.TYPES.APPLY.name());
		applyDto.setAllClassroom(false);

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(mockListApplicationJpaRepository, times(0)).save(any());
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

		when(classroomService.findById(any(UUID.class))).thenReturn(classroomDTO);
		when(classroomService.findEnrollments(any())).thenReturn(Collections.emptySet());

		// Run the test
		listApplicationServiceUnderTest.applyListTo(applyDto);

		// Verify the results
		verify(classroomService, times(1)).findById(any(UUID.class));
		verify(classroomService, times(1)).findEnrollments(any());
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

	@Test
	public void testCountQuestionsFirstTime() {
		List<QuestionDTO> questions = Fixture.from(QuestionDTO.class).gimme(2, QuestionDTOTemplate.TYPES.QUESTION.name());
		when(questionCountJpaRepository.findAllByQuestionsAndInstructor(anyList(), anyString())).thenReturn(new ArrayList<QuestionCount>());
		when(questionCountJpaRepository.findByQuestionAndInstructor(any(UUID.class), anyString())).thenReturn(Optional.empty());
		when(questionCountJpaRepository.save(any(QuestionCount.class))).thenReturn(null);

		listApplicationServiceUnderTest.countQuestions(UUID.randomUUID().toString(), questions);
	}

	@Test
	public void testCountQuestionsSecondTime() {
		List<QuestionDTO> questions = Fixture.from(QuestionDTO.class).gimme(1, QuestionDTOTemplate.TYPES.QUESTION.name());
		List<QuestionCount> questionCountList = new ArrayList<>();

		QuestionCount questionCount = new QuestionCount();
		questionCount.setInstructor(UUID.randomUUID().toString());
		questionCount.setQuestion(UUID.randomUUID());
		questionCount.setCounter(1);
		questionCountList.add(questionCount);

		when(questionCountJpaRepository.findAllByQuestionsAndInstructor(anyList(), anyString())).thenReturn(questionCountList);
		when(questionCountJpaRepository.findByQuestionAndInstructor(any(UUID.class), anyString())).thenReturn(Optional.of(questionCount));

		doAnswer(new org.mockito.stubbing.Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				return null;
			}
		}).when(questionCountJpaRepository).updateCounter(any(UUID.class), anyInt());

		listApplicationServiceUnderTest.countQuestions(UUID.randomUUID().toString(), questions);
	}

	@Test
	public void testFinishListApplication() {
		final ListApplication listApplicationUnfinished = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.UNFINISHED_APPLICATION.name());
		final ListApplication listApplicationFinished = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());

		when(mockListApplicationJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(listApplicationUnfinished));
		when(mockListApplicationJpaRepository.save(listApplicationUnfinished)).thenReturn(listApplicationFinished);

		final ListApplicationDTO result = listApplicationServiceUnderTest.finishListApplication(listApplicationUnfinished.getId());

		verify(mockListApplicationJpaRepository, times(1)).findById(any(UUID.class));
		verify(mockListApplicationJpaRepository, times(1)).save(any());

		assertEquals(ApplicationListStatus.ENCERRADA, result.getStatus());
	}

}
