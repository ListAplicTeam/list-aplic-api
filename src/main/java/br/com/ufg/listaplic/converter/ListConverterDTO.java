package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.model.Answer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public final class ListConverterDTO {

    private ListConverterDTO() {
    }

    public static ListDTO fromListIntegrationToListDTO(final ListIntegrationDTO listIntegrationDTO) {
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listIntegrationDTO.getId());
        listDTO.setName(listIntegrationDTO.getTitulo());
        listDTO.setUser(listIntegrationDTO.getUsuario());

        List<QuestaoIntegrationDTO> questions = listIntegrationDTO.getQuestoes();

        Double averageDifficultyLevel = questions.stream()
                .mapToInt(QuestaoIntegrationDTO::getNivelDificuldade)
                .average()
                .orElse(BigDecimal.ZERO.doubleValue());
        listDTO.setDifficultyLevel(averageDifficultyLevel.intValue());

        Set<DisciplinaIntegrationDTO> subjects = questions.stream()
                .map(QuestaoIntegrationDTO::getDisciplina)
                .collect(Collectors.toSet());
        listDTO.setSubjects(subjects);

        Set<AreaDoConhecimentoDTO> knowledgeAreas = questions.stream()
                .map(QuestaoIntegrationDTO::getAreaDeConhecimento)
                .collect(Collectors.toSet());
        listDTO.setKnowledgeAreas(knowledgeAreas);

        Set<String> tags = questions.stream()
                .map(QuestaoIntegrationDTO::getTags)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        listDTO.setTags(tags);

        listDTO.setQuestions(listIntegrationDTO.getQuestoes().stream()
                .map(QuestionConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList()));

		Integer answerTime = questions.stream()
				.mapToInt(QuestaoIntegrationDTO::getTempoMaximoDeResposta)
				.sum();
		listDTO.setAnswerTime(answerTime);

        return listDTO;
    }

    public static List<Answer> fromListDTOToListAnswer(final UUID userId, final ListDTO listDTO) {
        List<Answer> answers = new ArrayList<>();
        for (QuestionDTO questionDTO : listDTO.getQuestions()) {
            Answer answer = new Answer();
            answer.setApplicationId(listDTO.getListApplicationId());
            answer.setQuestionId(questionDTO.getId());
            answer.setAnswer(questionDTO.getAnswer());
            answer.setUserId(userId);
            answers.add(answer);
        }
        return answers;
    }

}
