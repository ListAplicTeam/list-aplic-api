package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.model.Answer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ListConverterDTO {

    private ListConverterDTO() {
    }

    public static ListDTO fromListIntegrationToDTO(final ListIntegrationDTO listIntegrationDTO) {
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listIntegrationDTO.getId());
        listDTO.setName(listIntegrationDTO.getTitulo());
//        listDTO.setUser(listIntegrationDTO.getUsuario());
        listDTO.setUser(listIntegrationDTO.getDiscursivas().stream().findFirst().get().getUsuario());
        listDTO.setSubjectCode(listIntegrationDTO.getDisciplina().getCodigo());

        List<QuestionDTO> questions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(listIntegrationDTO.getDiscursivas())) {
            questions.addAll(listIntegrationDTO.getDiscursivas().stream()
                    .map(QuestionConverterDTO::fromDiscursivasIntegrationToDTO)
                    .collect(Collectors.toList()));
        }

        if (!CollectionUtils.isEmpty(listIntegrationDTO.getObjetivas())) {
            questions.addAll(listIntegrationDTO.getObjetivas().stream()
                    .map(QuestionConverterDTO::fromObjetivasIntegrationToDTO)
                    .collect(Collectors.toList()));
        }

        listDTO.setQuestions(questions);

        return listDTO;
    }

    public static List<Answer> fromListDTOToListAnswer(final ListDTO listDTO) {
        List<Answer> answers = new ArrayList<>();
        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
            Answer answer = new Answer();
            answer.setListId(listDTO.getId());
            answer.setQuestionId(questionDTO.getId());
            answer.setAnswer(questionDTO.getAnswer());
            answers.add(answer);
        }
        return answers;
    }

}
