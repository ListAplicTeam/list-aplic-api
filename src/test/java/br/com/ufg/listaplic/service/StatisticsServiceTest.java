package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.StatisticsDTO;
import br.com.ufg.listaplic.model.QuestionCount;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.EnrollmentJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.QuestionCountJpaRepository;
import br.com.ufg.listaplic.template.QuestionCountTemplate;
import br.com.ufg.listaplic.template.QuestionDTOTemplate;
import br.com.ufg.listaplic.util.AnswerCount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest extends BaseTest {

    @InjectMocks
    private StatisticsService statisticsServiceUnderTest;

    @Mock
    private AnswerJpaRepository mockAnswerJpaRepository;

    @Mock
    private EnrollmentJpaRepository mockEnrollmentJpaRepository;

    @Mock
    private ListApplicationJpaRepository mockListApplicationJpaRepository;

    @Mock
    private QuestionCountJpaRepository mockQuestionCountJpaRepository;

    @Mock
    private ListElabNetwork mockListElabNetwork;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCalculateClassroomStatistics() {
        List<AnswerCount> answerCountList = new ArrayList<>();

        AnswerCount answerCount = new AnswerCount() {

            private String application = UUID.randomUUID().toString();

            private Integer quantity = 2;

            @Override
            public String getApplication() {
                return this.application;
            }

            @Override
            public Integer getQuantity() {
                return this.quantity;
            }
        };

        answerCountList.add(answerCount);
        when(mockAnswerJpaRepository.findAnswerCountsByClassroomId(any(UUID.class))).thenReturn(answerCountList);

        Integer studentCount = 2;
        when(mockEnrollmentJpaRepository.countStudentsByClassroomId(any(UUID.class))).thenReturn(studentCount);

        Integer applicationCount = 1;
        when(mockListApplicationJpaRepository.countByClassroomId(any(UUID.class))).thenReturn(applicationCount);

        StatisticsDTO result = statisticsServiceUnderTest.calculateClassroomStatistics(UUID.randomUUID());

        assertEquals(result.getCompletionPercentage(), new Float(1.00));
    }

    @Test
    public void testCalculateInstructorStatistics() {
        List<QuestionCount> questionCountList = Fixture.from(QuestionCount.class).gimme(5, QuestionCountTemplate.TYPES.QUESTION_COUNT.name());
        when(mockQuestionCountJpaRepository.findAllByInstructor(anyString())).thenReturn(questionCountList);

        QuestionDTO questionDTO = Fixture.from(QuestionDTO.class).gimme(QuestionDTOTemplate.TYPES.QUESTION.name());
        when(mockListElabNetwork.getQuestionById(any(UUID.class))).thenReturn(questionDTO);

        StatisticsDTO result = statisticsServiceUnderTest.calculateInstructorStatistics(UUID.randomUUID().toString());

        assertEquals(result.getTopFiveQuestions().size(), 5);
    }

    @Test
    public void testCalculateClassroomStatisticsWithStudentError() {
        List<AnswerCount> answerCountList = new ArrayList<>();

        when(mockAnswerJpaRepository.findAnswerCountsByClassroomId(any(UUID.class))).thenReturn(answerCountList);

        Integer studentCount = 0;
        when(mockEnrollmentJpaRepository.countStudentsByClassroomId(any(UUID.class))).thenReturn(studentCount);

        StatisticsDTO result = statisticsServiceUnderTest.calculateClassroomStatistics(UUID.randomUUID());

        assertEquals(result.getErrorMessage(), StatisticsService.STUDENT_ERROR);
    }

    @Test
    public void testCalculateClassroomStatisticsWithApplicationError() {
        List<AnswerCount> answerCountList = new ArrayList<>();

        when(mockAnswerJpaRepository.findAnswerCountsByClassroomId(any(UUID.class))).thenReturn(answerCountList);

        Integer studentCount = 1;
        when(mockEnrollmentJpaRepository.countStudentsByClassroomId(any(UUID.class))).thenReturn(studentCount);

        Integer applicationCount = 0;
        when(mockListApplicationJpaRepository.countByClassroomId(any(UUID.class))).thenReturn(applicationCount);

        StatisticsDTO result = statisticsServiceUnderTest.calculateClassroomStatistics(UUID.randomUUID());

        assertEquals(result.getErrorMessage(), StatisticsService.APPLICATION_ERROR);
    }

    @Test
    public void testShouldGetErrorMessageWhenExceptionOccur() {
        Mockito.doThrow(new IllegalArgumentException(StatisticsService.APPLICATION_ERROR)).when(mockQuestionCountJpaRepository).findAllByInstructor(anyString());

        StatisticsDTO result = statisticsServiceUnderTest.calculateInstructorStatistics("INSTRUCTOR");

        assertEquals(result.getErrorMessage(), StatisticsService.APPLICATION_ERROR);
    }
}
