package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ListConverterDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.network.ListElabNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private ListElabNetwork listElabNetwork;

    @Autowired
    private AnswerService answerService;

    public List<ListDTO> findList(String name, String subjectCode, boolean aleatory, boolean onlyPending) {

        // todas as listas
        List<ListDTO> allLists = getAllLists();

        if (onlyPending) {
            // ids das listas que foram respondidas
            Set<UUID> answersIdList = answerService.findAll().stream()
                    .map(Answer::getListId)
                    .collect(Collectors.toSet());

            // filtra as que estão pendentes
            allLists = allLists.stream()
                    .filter(listDTO -> !answersIdList.contains(listDTO.getId()))
                    .collect(Collectors.toList());
        }

        if (aleatory) {
            return Collections.singletonList(allLists.stream().findAny().orElse(new ListDTO()));
        }

        if (Objects.nonNull(name) && Objects.nonNull(subjectCode)) {
            return allLists.stream()
                    .filter(listDTO -> listDTO.getName().contains(name))
                    .filter(listDTO -> listDTO.getSubjectCode().equals(subjectCode))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(name)) {
            return allLists.stream()
                    .filter(listDTO -> listDTO.getName().contains(name))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(subjectCode)) {
            return allLists.stream()
                    .filter(listDTO -> listDTO.getSubjectCode().equals(subjectCode))
                    .collect(Collectors.toList());
        }

        return allLists;
    }

    public void answeringList(ListDTO listDTO) {
        List<Answer> answers = ListConverterDTO.fromListDTOToListAnswer(listDTO);
        answerService.saveAll(answers);
    }

    private List<ListDTO> getAllLists() {
        return listElabNetwork.getLists();
    }

}
