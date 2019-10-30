package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListApplicationService {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ListApplicationJpaRepository listApplicationJpaRepository;

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

            listApplicationJpaRepository.save(listApplication);
        } else {
            //do stuff
        }
    }
}
