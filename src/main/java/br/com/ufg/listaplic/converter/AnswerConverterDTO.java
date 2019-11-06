package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.model.Answer;

import java.util.UUID;

public final class AnswerConverterDTO {

    public AnswerConverterDTO() {
    }

    public static AnswerDTO fromDomainAndListIdToAnswerDTO(Answer answer, UUID listId) {
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setListId(listId);
        answerDTO.setQuestionId(answer.getQuestionId());
        answerDTO.setAnswer(answer.getAnswer());

        return answerDTO;
    }
}
