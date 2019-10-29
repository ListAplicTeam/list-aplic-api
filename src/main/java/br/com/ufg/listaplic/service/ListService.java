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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private ListElabNetwork listElabNetwork;

    @Autowired
    private AnswerService answerService;

    public List<ListDTO> findListByInstructor(String user, String name, String subjectCode, boolean aleatory) {

        // todas as listas
        List<ListDTO> allLists = getAllLists();

        // filtrando apenas as listas do usuário
        allLists = allLists.stream().filter(listDTO -> user.equals(listDTO.getUser())).collect(Collectors.toList());

        if (aleatory) {
            allLists = Collections.singletonList(allLists.stream().findAny().orElse(new ListDTO()));
        }

        if (Objects.nonNull(name)) {
            allLists = allLists.stream()
                    .filter(listDTO -> listDTO.getName().contains(name))
                    .collect(Collectors.toList());
        }

        if (Objects.nonNull(subjectCode)) {
            allLists = allLists.stream()
                    .filter(listDTO -> listDTO.getSubjectCode().equals(subjectCode))
                    .collect(Collectors.toList());
        }

        return allLists;
    }

    public void answeringList(UUID userId, ListDTO listDTO) {
        List<Answer> answers = ListConverterDTO.fromListDTOToListAnswer(userId, listDTO);
        answerService.saveAll(answers);
    }

    private List<ListDTO> getAllLists() {
        return listElabNetwork.getLists();
    }

}
