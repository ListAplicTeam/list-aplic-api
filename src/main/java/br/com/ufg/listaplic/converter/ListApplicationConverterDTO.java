package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.ListApplication;

import java.util.List;

public class ListApplicationConverterDTO {

    public static ListApplicationDTO fromListApplicationAndStudentsToListApplicationDTO(ListApplication listApplication,
                                                                                        List<StudentDTO> students) {
        ListApplicationDTO listApplicationDTO = new ListApplicationDTO();
        listApplicationDTO.setId(listApplication.getId());
        listApplicationDTO.setGroupId(listApplication.getClassroom().getId());
        listApplicationDTO.setListId(listApplication.getList());
        listApplicationDTO.setApplicationDateTime(listApplication.getApplicationDateTime());
        listApplicationDTO.setStatus(listApplication.getStatus());
        listApplicationDTO.setStudentList(students);

        return listApplicationDTO;
    }
}
