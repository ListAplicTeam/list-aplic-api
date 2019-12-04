package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.dto.listelab.DisciplinaIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.QuestoesIntegrationDTO;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListConverterDTO {

	private ListConverterDTO() {
	}

	public static ListDTO fromListIntegrationToListDTO(final ListIntegrationDTO listIntegrationDTO) {
		ListDTO listDTO = new ListDTO();
		listDTO.setId(listIntegrationDTO.getId());
		listDTO.setName(listIntegrationDTO.getTitulo());
		listDTO.setUser(listIntegrationDTO.getUsuario());

		List<QuestoesIntegrationDTO<?>> questions = Stream.of(
				listIntegrationDTO.getQuestoesDiscursiva().stream(),
				listIntegrationDTO.getQuestoesMultiplaEscolha().stream(),
				listIntegrationDTO.getQuestoesVerdadeiroOuFalso().stream(),
				listIntegrationDTO.getQuestoesAssociacaoDeColunas().stream())
				.flatMap(i -> i)
				.collect(Collectors.toList());

		Integer difficultyLevel = listIntegrationDTO.getNivelDeDificuldade();
		listDTO.setDifficultyLevel(difficultyLevel);

		Set<DisciplinaIntegrationDTO> subjects = questions.stream()
				.map(question -> question.getQuestao().getDisciplina())
				.collect(Collectors.toSet());
		listDTO.setSubjects(subjects);

		Set<AreaDoConhecimentoDTO> knowledgeAreas = questions.stream()
				.map(question -> question.getQuestao().getAreaDeConhecimento())
				.collect(Collectors.toSet());
		listDTO.setKnowledgeAreas(knowledgeAreas);

		Set<String> tags = questions.stream()
				.map(question -> question.getQuestao().getTags())
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());
		listDTO.setTags(tags);

		Integer answerTime = questions.stream()
				.mapToInt(question -> question.getQuestao().getTempoMaximoDeResposta())
				.sum();
		listDTO.setAnswerTime(answerTime);

		listDTO.setQuestions(questions.stream()
				.map(questoesIntegrationDTO -> QuestionConverterDTO.fromDomainToDTO(questoesIntegrationDTO.getQuestao()))
				.collect(Collectors.toList()));

		return listDTO;
	}

}
