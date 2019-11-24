package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaDTO;

import java.util.stream.Collectors;

public final class QuestionConverterDTO {

	private QuestionConverterDTO() {
	}

	public static QuestionDTO fromDomainToDTO(final QuestaoIntegrationDTO questaoIntegrationDTO) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(questaoIntegrationDTO.getId());
		questionDTO.setName(questaoIntegrationDTO.getEnunciado());
		questionDTO.setType(QuestionType.DISCURSIVE);

		String answer = questaoIntegrationDTO.getRespostaEsperada().stream()
				.map(RespostaEsperadaDTO::getDescricao)
				.collect(Collectors.joining(", "));
		questionDTO.setAnswer(answer);
		return questionDTO;
	}

}
