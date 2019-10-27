package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.network.ListElabNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private ListElabNetwork listElabNetwork;

    public List<ListDTO> findList(String name, String subjectCode, boolean aleatory) {

        if (aleatory) {
            return Collections.singletonList(getLists().stream().findAny().orElse(new ListDTO()));
        }

        if (Objects.nonNull(name) && Objects.nonNull(subjectCode)) {
            return getLists().stream()
                    .filter(listDTO -> listDTO.getName().contains(name))
                    .filter(listDTO -> listDTO.getSubjectCode().equals(subjectCode))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(name)) {
            return getLists().stream()
                    .filter(listDTO -> listDTO.getName().contains(name))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(subjectCode)) {
            return getLists().stream()
                    .filter(listDTO -> listDTO.getSubjectCode().equals(subjectCode))
                    .collect(Collectors.toList());
        }

        return getLists();
    }

    private List<ListDTO> getLists() {
        return listElabNetwork.getLists();
    }
}
