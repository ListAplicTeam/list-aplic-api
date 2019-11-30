package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.converter.ListApplicationConverterDTO;
import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.*;
import br.com.ufg.listaplic.exception.NoOneStudentOnClassroomException;
import br.com.ufg.listaplic.model.*;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListApplicationService {

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private ListApplicationJpaRepository listApplicationJpaRepository;

	@Autowired
	private StudentJpaRepository studentJpaRepository;

	@Autowired
	private ClassroomJpaRepository classroomJpaRepository;

	@Autowired
	private AnswerJpaRepository answerJpaRepository;

	@Autowired
	private ListElabNetwork listElabNetwork;

	@Autowired
	private QuestionCountJpaRepository questionCountJpaRepository;

	public List<ListApplication> findAll() {
		return listApplicationJpaRepository.findAll();
	}

	public void applyListTo(ApplyDTO applyDTO) {
		if (applyDTO.getAllClassroom()) {
			ClassroomDTO classroomDTO = classroomService.findById(applyDTO.getClassroomId());
			Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

			if (!this.checkIfTheresStudentsOnClassroom(classroom)) {
				throw new NoOneStudentOnClassroomException();
			}

			ListApplication listApplication = new ListApplication();
			listApplication.setClassroom(classroom);
			listApplication.setList(applyDTO.getListId());

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			listApplication.setApplicationDateTime(timestamp);

			listApplication.setStartDate(applyDTO.getStartDate());
			listApplication.setFinalDate(applyDTO.getFinalDate());

			ListApplication savedApplication = listApplicationJpaRepository.save(listApplication);

			if (savedApplication != null && savedApplication.getId() != null) {
				ListDTO listDTO = listElabNetwork.getListById(applyDTO.getListId());
				this.countQuestions(classroom.getInstructorId(), listDTO.getQuestions());
			}
		} else {
			//do stuff
		}
	}

	private boolean checkIfTheresStudentsOnClassroom(Classroom classroom) {
		final Set<Enrollment> enrollments = classroomService.findEnrollments(classroom);
		return enrollments != null && !enrollments.isEmpty();
	}

	protected void countQuestions(String instructorId, List<QuestionDTO> questions) {
		HashMap<UUID, Integer> questionCountMap = new HashMap<>();

		List<QuestionCount> questionCountList = questionCountJpaRepository
				.findAllByQuestionsAndInstructor(questions.stream()
						.map(QuestionDTO::getId)
						.collect(Collectors.toList()), instructorId);

		questionCountList.forEach(count -> {
			questionCountMap.put(count.getQuestion(), count.getCounter());
		});

		questions.forEach(q -> {
			if (questionCountMap.containsKey(q.getId())) {
				questionCountMap.replace(q.getId(), questionCountMap.get(q.getId()) + 1);
			} else {
				questionCountMap.put(q.getId(), 1);
			}
		});

		questionCountMap.forEach((key, value) -> {
			QuestionCount newQuestionCount = new QuestionCount(key, instructorId, value);

			if (!questionCountJpaRepository.findByQuestionAndInstructor(key, instructorId).isPresent()) {
				questionCountJpaRepository.save(newQuestionCount);
			} else {
				questionCountJpaRepository.updateCounter(newQuestionCount.getQuestion(), newQuestionCount.getCounter());
			}
		});
	}

	public List<ListApplicationDTO> getListsByClassroom(UUID classroomId, @Nullable ApplicationListStatus status) {

		Classroom classroom = classroomJpaRepository.findById(classroomId).orElse(new Classroom());
		List<ListApplication> listApplications = status != null ?
				listApplicationJpaRepository.findByClassroomAndStatus(classroom, status) :
				listApplicationJpaRepository.findByClassroom(classroom);
		List<Student> students = studentJpaRepository.findStudentsByClassroomId(classroomId);
		List<StudentDTO> studentDTOList = students.stream()
				.map(StudentConverterDTO::fromDomainToDTO)
				.collect(Collectors.toList());

		return listApplications.stream()
				.map(listApplication -> ListApplicationConverterDTO.fromDomainToDTO(
						listApplication,
						studentDTOList,
						null,
						listElabNetwork.getListById(listApplication.getList())))
				.collect(Collectors.toList());
	}

	public ListApplicationDTO getListApplicationDetail(UUID applicationId) {
		ListApplication application = listApplicationJpaRepository.findById(applicationId).orElse(new ListApplication());
		List<Answer> answers = answerJpaRepository.findByApplicationId(applicationId);
		List<Student> students = studentJpaRepository.findStudentsByClassroomId(application.getClassroom().getId());
		List<StudentDTO> studentDTOList = students.stream()
				.map(StudentConverterDTO::fromDomainToDTO)
				.collect(Collectors.toList());
		List<AnswerDTO> answerDTOList = answers.isEmpty() ? null : answers.stream().map(a -> AnswerConverterDTO.fromDomainAndListIdToAnswerDTO(a, application.getList()))
				.collect(Collectors.toList());
		ListDTO listDTO = listElabNetwork.getListById(application.getList());

		return ListApplicationConverterDTO.fromDomainToDTO(application,
				studentDTOList,
				answerDTOList,
				listDTO);
	}

	public ListApplication save(ListApplication listApplication) {
		return listApplicationJpaRepository.save(listApplication);
	}

	public ListApplicationDTO finishListApplication(UUID applicationId) {
		ListApplication application = listApplicationJpaRepository.findById(applicationId).orElse(new ListApplication());
		application.setStatus(ApplicationListStatus.ENCERRADA);
		ListApplication savedApplication = listApplicationJpaRepository.save(application);
		return ListApplicationConverterDTO.fromDomainToDTO(savedApplication, null, null, new ListDTO());
	}
}
