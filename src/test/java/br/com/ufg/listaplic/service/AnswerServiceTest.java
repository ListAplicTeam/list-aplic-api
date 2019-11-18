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

}
