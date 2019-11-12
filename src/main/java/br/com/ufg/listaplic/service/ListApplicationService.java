package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.converter.ListApplicationConverterDTO;
import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

            listApplicationJpaRepository.save(listApplication);
        } else {
            //do stuff
        }
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
