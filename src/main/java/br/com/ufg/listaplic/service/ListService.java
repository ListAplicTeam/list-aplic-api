package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.AnswerConverterDTO;
import br.com.ufg.listaplic.dto.AnswerStatusType;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.FilterList;
import br.com.ufg.listaplic.exception.ListApplicationFinishedException;
import br.com.ufg.listaplic.exception.ResourceNotFoundException;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ListDTO> getPendingListsByStudent(UUID classroomId, UUID studentId) {
        Classroom classroom = classroomService.findClassroomById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));
        List<ListApplication> listApplications = listApplicationJpaRepository.findByClassroomAndStatusNot(classroom, ApplicationListStatus.NAO_INICIADA);

        return listApplications.stream()
                .map(listApplication -> getListById(listApplication, studentId))
                .collect(Collectors.toList());
    }

    private ListDTO getListById(ListApplication listApplication, UUID studentId) {
        ListDTO listDTO = listElabNetwork.getListById(listApplication.getList());
        listDTO.setListApplicationId(listApplication.getId());

        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
            Optional<Answer> answerOptional = answerService.findByApplicationIdAndQuestionIdAndUserId(listDTO.getListApplicationId(), questionDTO.getId(), studentId);
            if (answerOptional.isPresent()) {
                Answer answer = answerOptional.get();
                questionDTO.setAnswer(answer.getAnswer());
                listDTO.setStatus(answer.getStatusType().equals(AnswerStatusType.DRAFT) ? ApplicationListStatus.EM_ANDAMENTO : ApplicationListStatus.ENCERRADA);
            } else {
                listDTO.setStatus(ApplicationListStatus.NAO_INICIADA);
            }

            if (listApplication.getStatus().isEncerrada()) {
                listDTO.setStatus(ApplicationListStatus.ENCERRADA);
            }
        }

        return listDTO;
    }

    private List<ListDTO> getListsByFilter(FilterList filterList) {
        return listElabNetwork.getListsByFilter(filterList);
    }

    public void answeringList(AnswerStatusType answerStatusType, UUID userId, ListDTO listDTO) {
        ListApplication listApplication = listApplicationJpaRepository.findById(listDTO.getListApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("List not found"));

        if (listApplication.getStatus().isEncerrada()) {
            throw new ListApplicationFinishedException();
        }

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
