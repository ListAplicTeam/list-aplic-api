package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.AnswerDTO;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.template.AnswerTemplate;
import br.com.ufg.listaplic.template.QuestionDTOTemplate;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class AnswerConverterDTOTest extends BaseTest {

    @Test
    public void whenConvertingQuestionDTOToAnswer_thenSuccess() {
        // Setup
        final QuestionDTO questionDTO = Fixture.from(QuestionDTO.class).gimme(QuestionDTOTemplate.TYPES.QUESTION.name());
        UUID answerId = UUID.randomUUID();
        UUID listApplicationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        AnswerStatusType status = AnswerStatusType.SAVE;

        final Answer answer = AnswerConverterDTO.fromQuestionDTOToAnswer(questionDTO, answerId, listApplicationId, status, userId);

        // Verify the results
        assertEquals(answerId, answer.getId());
        assertEquals(listApplicationId, answer.getApplicationId());
        assertEquals(questionDTO.getId(), answer.getQuestionId());
        assertEquals(questionDTO.getAnswer(), answer.getAnswer());
        assertEquals(userId, answer.getUserId());
        assertEquals(status, answer.getStatusType());
    }

    @Test
    public void whenConvertingQuestionDTOToAnswerWithoutAnswerId_thenSuccess() {
        // Setup
        final QuestionDTO questionDTO = Fixture.from(QuestionDTO.class).gimme(QuestionDTOTemplate.TYPES.QUESTION.name());
        UUID listApplicationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        AnswerStatusType status = AnswerStatusType.SAVE;

        final Answer answer = AnswerConverterDTO.fromQuestionDTOToAnswer(questionDTO, null, listApplicationId, status, userId);

        // Verify the results
        assertEquals(null, answer.getId());
        assertEquals(listApplicationId, answer.getApplicationId());
        assertEquals(questionDTO.getId(), answer.getQuestionId());
        assertEquals(questionDTO.getAnswer(), answer.getAnswer());
        assertEquals(userId, answer.getUserId());
        assertEquals(status, answer.getStatusType());
    }

    @Test
    public void whenConvertingAnswerToAnswerDTO_thenSuccess() {
        // Setup
        final Answer answer = Fixture.from(Answer.class).gimme(AnswerTemplate.TYPES.ANSWER.name());
        UUID listId = UUID.randomUUID();

        final AnswerDTO answerDTO = AnswerConverterDTO.fromDomainAndListIdToAnswerDTO(answer, listId);

        // Verify the results
        assertEquals(listId, answerDTO.getListId());
        assertEquals(answer.getQuestionId(), answerDTO.getQuestionId());
        assertEquals(answer.getAnswer(), answerDTO.getAnswer());
        assertEquals(answer.getStatusType(), answerDTO.getStatus());
    }

}
