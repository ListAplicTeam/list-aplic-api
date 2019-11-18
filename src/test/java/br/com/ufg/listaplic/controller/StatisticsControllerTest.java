package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.StatisticsDTO;
import br.com.ufg.listaplic.service.StatisticsService;
import br.com.ufg.listaplic.template.StatisticsDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StatisticsControllerTest extends BaseTest {

    @InjectMocks
    private StatisticsController statisticsControllerUnderTest;

    @Mock
    private StatisticsService mockStatisticsService;

    @Test
    public void testGetClassroomStatistics() {
        // Setup
        StatisticsDTO statisticsDTO = Fixture.from(StatisticsDTO.class).gimme(StatisticsDTOTemplate.TYPES.CLASSROOM_STATISTICS.name());
        when(mockStatisticsService.calculateClassroomStatistics(any(UUID.class))).thenReturn(statisticsDTO);

        // Run the test
        StatisticsDTO result = statisticsControllerUnderTest.getClassroomStatistics(UUID.randomUUID());

        // Verify the results
        assertEquals(result.getCompletionPercentage(), statisticsDTO.getCompletionPercentage());
    }

    @Test
    public void testGetInstructorStatistics() {
        // Setup
        StatisticsDTO statisticsDTO = Fixture.from(StatisticsDTO.class).gimme(StatisticsDTOTemplate.TYPES.INSTRUCTOR_STATISTICS.name());
        when(mockStatisticsService.calculateInstructorStatistics(anyString())).thenReturn(statisticsDTO);

        // Run the test
        StatisticsDTO result = statisticsControllerUnderTest.getInstructorStatistics(UUID.randomUUID().toString());

        // Verify the results
        assertEquals(result.getTopFiveQuestions().size(), statisticsDTO.getTopFiveQuestions().size());
    }
}
