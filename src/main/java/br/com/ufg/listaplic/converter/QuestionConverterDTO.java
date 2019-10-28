package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.DiscursivasIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.ObjetivasIntegrationDTO;

import java.util.ArrayList;

public final class QuestionConverterDTO {

    private QuestionConverterDTO() {
    }

    public static QuestionDTO fromDiscursivasIntegrationToDTO(final DiscursivasIntegrationDTO discursivasIntegrationDTO) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(discursivasIntegrationDTO.getId());
        questionDTO.setName(discursivasIntegrationDTO.getEnunciado());
        questionDTO.setType(QuestionType.DISCURSIVE);
        return questionDTO;
    }

    public static QuestionDTO fromObjetivasIntegrationToDTO(final ObjetivasIntegrationDTO objetivasIntegrationDTO) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(objetivasIntegrationDTO.getId());
        questionDTO.setName(objetivasIntegrationDTO.getEnunciado());
        questionDTO.setType(QuestionType.OBJECTIVE);
        //TODO when list-elab implement objective question
        questionDTO.setOptions(new ArrayList<>());
        return questionDTO;
    }
}
