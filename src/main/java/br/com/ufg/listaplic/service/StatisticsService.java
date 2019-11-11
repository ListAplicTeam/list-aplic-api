package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.AnswerCountDTO;
import br.com.ufg.listaplic.dto.StatisticsDTO;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.EnrollmentJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatisticsService {

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Autowired
    private EnrollmentJpaRepository enrollmentJpaRepository;

    @Autowired
    private ListApplicationJpaRepository listApplicationJpaRepository;

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
}
