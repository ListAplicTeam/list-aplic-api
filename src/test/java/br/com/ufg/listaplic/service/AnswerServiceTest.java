package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.template.AnswerTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerServiceTest extends BaseTest {

    @InjectMocks
    private AnswerService answerServiceUnderTest;

    @Mock
    private AnswerJpaRepository mockAnswerJpaRepository;

    @Test
    public void testSaveAll() {
        // Setup
        final List<Answer> answers = Fixture.from(Answer.class).gimme(2, AnswerTemplate.TYPES.ANSWER.name());

        when(mockAnswerJpaRepository.saveAll(anyList())).thenReturn(answers);

        // Run the test
        answerServiceUnderTest.saveAll(answers);

        // Verify the results
        verify(mockAnswerJpaRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testFindByApplicationIdAndQuestionIdAndUserId() {
        // Setup
        final Answer answer = Fixture.from(Answer.class).gimme(AnswerTemplate.TYPES.ANSWER.name());

        when(mockAnswerJpaRepository.findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(Optional.of(answer));

        // Run the test
        Optional<Answer> answerDB = answerServiceUnderTest.findByApplicationIdAndQuestionIdAndUserId(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        // Verify the results
        verify(mockAnswerJpaRepository, times(1)).findByApplicationIdAndQuestionIdAndUserId(any(UUID.class), any(UUID.class), any(UUID.class));

        assertEquals(answer.getId(), answerDB.get().getId());
        assertEquals(answer.getStatusType(), answerDB.get().getStatusType());
        assertEquals(answer.getAnswer(), answerDB.get().getAnswer());
        assertEquals(answer.getQuestionId(), answerDB.get().getQuestionId());
        assertEquals(answer.getApplicationId(), answerDB.get().getApplicationId());
        assertEquals(answer.getUserId(), answerDB.get().getUserId());
    }

}
