package br.com.ufg.listaplic.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.ufg.listaplic.dto.QuestionCountDTO;
import br.com.ufg.listaplic.dto.StatisticsDTO;
import br.com.ufg.listaplic.service.StatisticsService;

public class StatisticsDTOTemplate implements TemplateLoader {

    private static final String COMPLETION_PERCENTAGE = "completionPercentage";
    private static final String TOP_FIVE_QUESTIONS = "topFiveQuestions";
    private static final String ERROR_MESSAGE = "errorMessage";

    public enum TYPES {
        CLASSROOM_STATISTICS,
        INSTRUCTOR_STATISTICS,
        STATISTICS_WITH_STUDENT_ERROR,
        STATISTICS_WITH_APPLICATION_ERROR
    }

    @Override
    public void load() {
        buildClassroomStatistics();
        buildInstructorStatistics();
    }

    private void buildClassroomStatistics() {
        Fixture.of(StatisticsDTO.class).addTemplate(TYPES.CLASSROOM_STATISTICS.name(), new Rule() {{
            add(COMPLETION_PERCENTAGE, Float.parseFloat("00.45"));
        }});
    }

    private void buildInstructorStatistics() {
        Fixture.of(StatisticsDTO.class).addTemplate(TYPES.INSTRUCTOR_STATISTICS.name(), new Rule() {{
            add(TOP_FIVE_QUESTIONS, has(5).of(QuestionCountDTO.class, QuestionCountDTOTemplate.TYPES.QUESTION_COUNT_DTO.name()));
        }});
    }

    private void buildStatisticsWithStudentError() {
        Fixture.of(StatisticsDTO.class).addTemplate(TYPES.INSTRUCTOR_STATISTICS.name(), new Rule() {{
            add(ERROR_MESSAGE, StatisticsService.STUDENT_ERROR);
        }});
    }

    private void buildStatisticsWithApplicationError() {
        Fixture.of(StatisticsDTO.class).addTemplate(TYPES.INSTRUCTOR_STATISTICS.name(), new Rule() {{
            add(ERROR_MESSAGE, StatisticsService.APPLICATION_ERROR);
        }});
    }
}
