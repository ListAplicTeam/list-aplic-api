package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.DiscursivasIntegrationDTO;
import br.com.ufg.listaplic.dto.listelab.ObjetivasIntegrationDTO;
import br.com.ufg.listaplic.template.DiscursivasIntegrationDTOTemplate;
import br.com.ufg.listaplic.template.ObjetivasIntegrationDTOTemplate;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class QuestionConverterDTOTest extends BaseTest {

    @Test
    public void testFromDiscursivasIntegrationToQuestionDTO() {
        // Setup
        final DiscursivasIntegrationDTO discursivasIntegrationDTO = Fixture.from(DiscursivasIntegrationDTO.class).gimme(DiscursivasIntegrationDTOTemplate.TYPES.QUESTION_1.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDiscursivasIntegrationToQuestionDTO(discursivasIntegrationDTO);

        // Verify the results
        assertEquals(discursivasIntegrationDTO.getId(), result.getId());
        assertEquals(discursivasIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.DISCURSIVE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
    }

    @Test
    public void testFromInstructorToUser() {
        // Setup
        final ObjetivasIntegrationDTO objetivasIntegrationDTO = Fixture.from(ObjetivasIntegrationDTO.class).gimme(ObjetivasIntegrationDTOTemplate.TYPES.QUESTION_1.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromObjetivasIntegrationToQuestionDTO(objetivasIntegrationDTO);

        // Verify the results
        assertEquals(objetivasIntegrationDTO.getId(), result.getId());
        assertEquals(objetivasIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.OBJECTIVE, result.getType());
        assertEquals(Collections.emptyList(), result.getOptions());
        assertEquals(null, result.getAnswer());
    }
}
