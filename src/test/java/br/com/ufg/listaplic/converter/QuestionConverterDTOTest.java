package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.template.QuestaoIntegrationDTOTemplate;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class QuestionConverterDTOTest extends BaseTest {

    @Test
    public void testFromQuestionIntegrationToQuestionDTO() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.QUESTION_1.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.DISCURSIVE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals("", result.getExpectedAnswers());
    }

}
