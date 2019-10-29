package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListApplicationService {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ListApplicationJpaRepository listApplicationJpaRepository;

    @Autowired
    private ListElabNetwork listElabNetwork;

    public void applyListTo(Boolean allClassroom, String group, UUID classroomId, UUID list) {
        if (allClassroom) {
            ClassroomDTO classroomDTO = classroomService.findById(classroomId);
            ListDTO listDTO = listElabNetwork.getListById(list);

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
