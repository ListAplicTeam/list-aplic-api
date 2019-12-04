package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.model.Answer;

import java.util.Objects;
import java.util.UUID;

public final class AnswerConverterDTO {

    private AnswerConverterDTO() {
    }

    public static Answer fromQuestionDTOToAnswer(QuestionDTO questionDTO, UUID answerId, UUID listApplicationId, AnswerStatusType answerStatusType, UUID userId) {
        Answer answer = new Answer();
        if (Objects.nonNull(answerId)) {
            answer.setId(answerId);
        }
        answer.setApplicationId(listApplicationId);
        answer.setQuestionId(questionDTO.getId());
        answer.setAnswer(questionDTO.getAnswer());
        answer.setUserId(userId);
        answer.setStatusType(answerStatusType);
        return answer;
    }

    public static AnswerDTO fromDomainAndListIdToAnswerDTO(Answer answer, UUID listId) {
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setListId(listId);
        answerDTO.setQuestionId(answer.getQuestionId());
        answerDTO.setAnswer(answer.getAnswer());
        answerDTO.setStatus(answer.getStatusType());

        return answerDTO;
    }

}
