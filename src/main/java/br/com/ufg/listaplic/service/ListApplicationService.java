package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.converter.ListApplicationConverterDTO;
import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.*;
import br.com.ufg.listaplic.model.*;
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
            ListDTO listDTO = listElabNetwork.getListById(applyDTO.getListId());

            Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

            ListApplication listApplication = new ListApplication();
            listApplication.setClassroom(classroom);
            listApplication.setList(listDTO.getId());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            listApplication.setApplicationDateTime(timestamp);

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
                .map(a -> {
                    return ListApplicationConverterDTO.fromListApplicationAndStudentsToListApplicationDTO(a, studentDTOList, null);
                })
                .collect(Collectors.toList());
    }

    public ListApplicationDTO getListApplicationDetail(UUID applicationId) {
        ListApplication application = listApplicationJpaRepository.findById(applicationId).orElse(new ListApplication());
        List<Answer> answers = answerJpaRepository.findByApplicationId(applicationId);
        List<Student> students = studentJpaRepository.findStudentsByClassroomId(application.getClassroom().getId());
        List<StudentDTO> studentDTOList = students.stream()
                .map(StudentConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());
        List<AnswerDTO> answerDTOList = answers.stream().map(a -> AnswerConverterDTO.fromDomainAndListIdToAnswerDTO(a, application.getList()))
                .collect(Collectors.toList());

        return  ListApplicationConverterDTO.fromListApplicationAndStudentsToListApplicationDTO(application,
                studentDTOList,
                answerDTOList);
    }
}
