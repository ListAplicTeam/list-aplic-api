package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.dto.SubjectType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    public List<SubjectDTO> getSubjects() {
        return Arrays.asList(SubjectType.values()).stream()
                .map(subjectType -> new SubjectDTO(subjectType.getName(), subjectType.getCode()))
                .collect(Collectors.toList());
    }

}
