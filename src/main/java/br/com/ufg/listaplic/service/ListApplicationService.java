package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.converter.ListApplicationConverterDTO;
import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.*;
import br.com.ufg.listaplic.model.*;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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

    public void applyListTo(ApplyDTO applyDTO) {
        if (applyDTO.getAllClassroom()) {
            ClassroomDTO classroomDTO = classroomService.findById(applyDTO.getClassroomId());
            Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

            ListApplication listApplication = new ListApplication();
            listApplication.setClassroom(classroom);
            listApplication.setList(applyDTO.getListId());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            listApplication.setApplicationDateTime(timestamp);

            listApplication.setStartDate(applyDTO.getStartDate());
            listApplication.setFinalDate(applyDTO.getFinalDate());

            ListApplication savedApplication = listApplicationJpaRepository.save(listApplication);

            if(savedApplication != null && savedApplication.getId() != null) {
                ListDTO listDTO = listElabNetwork.getListById(applyDTO.getListId());
                this.countQuestions(classroom.getInstructorId(), listDTO.getQuestions());
            }
        } else {
            //do stuff
        }
    }

    private void countQuestions(String instructorId, List<QuestionDTO> questions) {
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

    public List<ListApplicationDTO> getFinishedListsByClassroomId(UUID classroomId) {
        Classroom classroom = classroomJpaRepository.findById(classroomId).orElse(new Classroom());
        List<ListApplication> listApplications = listApplicationJpaRepository.findByClassroomAndStatus(classroom, ApplicationListStatus.ENCERRADA);
        List<Student> students = studentJpaRepository.findStudentsByClassroomId(classroomId);
        List<StudentDTO> studentDTOList = students.stream()
                .map(StudentConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());

        return listApplications.stream()
                .map(listApplication -> ListApplicationConverterDTO.fromListApplicationAndStudentsToListApplicationDTO(listApplication, studentDTOList, null))
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

        return ListApplicationConverterDTO.fromListApplicationAndStudentsToListApplicationDTO(application,
                studentDTOList,
                answerDTOList);
    }
}
