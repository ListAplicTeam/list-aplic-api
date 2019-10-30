package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.model.Answer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class ListConverterDTO {

    private ListConverterDTO() {
    }

    public static ListDTO fromListIntegrationToListDTO(final ListIntegrationDTO listIntegrationDTO) {
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listIntegrationDTO.getId());
        listDTO.setName(listIntegrationDTO.getTitulo());
        listDTO.setUser(listIntegrationDTO.getUsuario());
        listDTO.setSubjectCode(listIntegrationDTO.getDisciplina().getCodigo());

        List<QuestionDTO> questions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(listIntegrationDTO.getDiscursivas())) {
            questions.addAll(listIntegrationDTO.getDiscursivas().stream()
                    .map(QuestionConverterDTO::fromDiscursivasIntegrationToQuestionDTO)
                    .collect(Collectors.toList()));
        }

        if (!CollectionUtils.isEmpty(listIntegrationDTO.getObjetivas())) {
            questions.addAll(listIntegrationDTO.getObjetivas().stream()
                    .map(QuestionConverterDTO::fromObjetivasIntegrationToQuestionDTO)
                    .collect(Collectors.toList()));
        }

        listDTO.setQuestions(questions);

        return listDTO;
    }

    public static List<Answer> fromListDTOToListAnswer(final UUID userId, final ListDTO listDTO) {
        List<Answer> answers = new ArrayList<>();
        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
            Answer answer = new Answer();
            answer.setApplicationId(listDTO.getListApplicationId());
            answer.setQuestionId(questionDTO.getId());
            answer.setAnswer(questionDTO.getAnswer());
            answer.setUserId(userId);
            answers.add(answer);
        }
        return answers;
    }

}
