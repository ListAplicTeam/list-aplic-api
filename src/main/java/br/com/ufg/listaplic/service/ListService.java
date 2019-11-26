package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.FilterList;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private ListElabNetwork listElabNetwork;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ListApplicationJpaRepository listApplicationJpaRepository;

    public List<ListDTO> getListsByFilter(@Nullable String subjectCode,
                                          @Nullable Integer difficultyLevel,
                                          @Nullable String knowledgeAreaCode,
                                          @Nullable Integer answerTime,
                                          @Nullable List<String> tags) {

        return getListsByFilter(buildFilterList(
                subjectCode,
                difficultyLevel,
                knowledgeAreaCode,
                answerTime,
                tags
        ));

    }

    public List<ListDTO> getPendingListsByStudent(UUID studentId) {
        List<UUID> classroomsId = classroomService.findByStudentId(studentId).stream()
                .map(ClassroomDTO::getId)
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(classroomsId)) {
            List<ListApplication> listApplications = listApplicationJpaRepository.findByClassrooms(classroomsId, studentId, AnswerStatusType.SAVE.name());

            return listApplications.stream()
                    .map(listApplication -> getListById(listApplication, studentId))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private ListDTO getListById(ListApplication listApplication, UUID studentId) {
        ListDTO listDTO = listElabNetwork.getListById(listApplication.getList());
        listDTO.setListApplicationId(listApplication.getId());
        listDTO.setStatus(listApplication.getStatus());

        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
            String answer = answerService.findByApplicationIdAndQuestionIdAndUserId(listDTO.getListApplicationId(), questionDTO.getId(), studentId)
                    .map(Answer::getAnswer)
                    .orElse(null);
            questionDTO.setAnswer(answer);
        }

        return listDTO;
    }

    private List<ListDTO> getListsByFilter(FilterList filterList) {
        return listElabNetwork.getListsByFilter(filterList);
    }

    public void answeringList(AnswerStatusType answerStatusType, UUID userId, ListDTO listDTO) {
        List<Answer> answers = new ArrayList<>();
        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
			UUID answerId = answerService.findByApplicationIdAndQuestionIdAndUserId(listDTO.getListApplicationId(), questionDTO.getId(), userId)
					.map(Answer::getId)
					.orElse(null);
			Answer answer = AnswerConverterDTO.fromQuestionDTOToAnswer(questionDTO, answerId, listDTO.getListApplicationId(), answerStatusType, userId);
            answers.add(answer);
        }
        answerService.saveAll(answers);
    }

    private FilterList buildFilterList(
            @Nullable String subjectCode,
            @Nullable Integer difficultyLevel,
            @Nullable String knowledgeAreaCode,
            @Nullable Integer answerTime,
            @Nullable List<String> tags
    ) {
        FilterList filterList = new FilterList();
        filterList.setTempoEsperadoResposta(answerTime);
        filterList.setNivelDificuldade(difficultyLevel);
        filterList.setAreaDeConhecimento(knowledgeAreaCode);
        filterList.setDisciplina(subjectCode);
        filterList.setTags(tags);

        return filterList;
    }

}
