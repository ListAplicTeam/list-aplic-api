package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.AnswerCountDTO;
import br.com.ufg.listaplic.dto.QuestionCountDTO;
import br.com.ufg.listaplic.dto.StatisticsDTO;
import br.com.ufg.listaplic.model.QuestionCount;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.EnrollmentJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.QuestionCountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Autowired
    private EnrollmentJpaRepository enrollmentJpaRepository;

    @Autowired
    private ListApplicationJpaRepository listApplicationJpaRepository;

    @Autowired
    private QuestionCountJpaRepository questionCountJpaRepository;

    @Autowired
    private ListElabNetwork listElabNetwork;

    public StatisticsDTO calculateClassroomStatistics(UUID classroomId) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        Integer totalAnswerCount = answerJpaRepository.findAnswerCountsByClassroomId(classroomId)
                .stream().map(AnswerCountDTO::getQuantity).reduce(0, Integer::sum);

        Integer studentCount = enrollmentJpaRepository.countStudentsByClassroomId(classroomId);

        if (studentCount == 0) {
            statisticsDTO.setErrorMessage("No students in group");
            return statisticsDTO;
        }

        Integer applicationCount = listApplicationJpaRepository.countByClassroomId(classroomId);

        if (applicationCount == 0) {
            statisticsDTO.setErrorMessage("No list applications for group");
            return statisticsDTO;
        }

        statisticsDTO.setCompletionPercentage((float) totalAnswerCount / (studentCount * applicationCount));

        return statisticsDTO;
    }

    public StatisticsDTO calculateInstructorStatistics(String instructor) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        try {
            List<QuestionCount> questionCountList = questionCountJpaRepository.findAllByInstructor(instructor);

            questionCountList.sort(Comparator.comparing(QuestionCount::getCounter));

            statisticsDTO.setTopFiveQuestions(questionCountList.stream()
                    .limit(5)
                    .map(c -> new QuestionCountDTO(listElabNetwork.getQuestionById(c.getQuestion()), c.getCounter()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            statisticsDTO.setErrorMessage(e.getMessage());
        }

        return statisticsDTO;
    }
}
