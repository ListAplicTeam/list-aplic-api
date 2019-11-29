package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaAssociacaoDeColunasDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaDiscursivaDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaMultiplaEscolhaDTO;
import br.com.ufg.listaplic.dto.listelab.RespostaEsperadaVerdadeiroOuFalsoDTO;

import java.util.List;

public final class QuestionConverterDTO {

    private QuestionConverterDTO() {
    }

    public static QuestionDTO fromDomainToDTO(final QuestaoIntegrationDTO<?> question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setName(question.getEnunciado());
        questionDTO.setExpectedAnswers(question.getRespostaEsperada());

        if (question.getRespostaEsperada() == null || question.getRespostaEsperada().get(0) instanceof RespostaEsperadaDiscursivaDTO) {
            questionDTO.setType(QuestionType.DISCURSIVE);
        } else if (question.getRespostaEsperada().get(0) instanceof RespostaEsperadaMultiplaEscolhaDTO) {
			questionDTO.setType(QuestionType.MULTIPLE_CHOICE);
		} else if (question.getRespostaEsperada().get(0) instanceof RespostaEsperadaVerdadeiroOuFalsoDTO) {
        	questionDTO.setType(QuestionType.TRUE_OR_FALSE);
        } else if (question.getRespostaEsperada().get(0) instanceof RespostaEsperadaAssociacaoDeColunasDTO) {
            questionDTO.setType(QuestionType.COLUMN_BINDING);
        }

        return questionDTO;
    }

}
