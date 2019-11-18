package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.ListApplication;
import org.springframework.lang.Nullable;

import java.util.List;

public final class ListApplicationConverterDTO {

    private ListApplicationConverterDTO() {
    }

    public static ListApplicationDTO fromListApplicationAndStudentsToListApplicationDTO(
            ListApplication listApplication,
            List<StudentDTO> students,
            @Nullable List<AnswerDTO> answers
    ) {
        ListApplicationDTO listApplicationDTO = new ListApplicationDTO();
        listApplicationDTO.setId(listApplication.getId());
        listApplicationDTO.setGroupId(listApplication.getClassroom().getId());
        listApplicationDTO.setListId(listApplication.getList());
        listApplicationDTO.setApplicationDateTime(listApplication.getApplicationDateTime());
        listApplicationDTO.setStartDate(listApplication.getStartDate());
        listApplicationDTO.setFinalDate(listApplication.getFinalDate());
        listApplicationDTO.setStatus(listApplication.getStatus());
        listApplicationDTO.setStudentList(students);
        listApplicationDTO.setAnswerList(answers);

        return listApplicationDTO;
    }
}
