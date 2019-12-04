package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.QuestionType;
import br.com.ufg.listaplic.dto.listelab.QuestaoIntegrationDTO;
import br.com.ufg.listaplic.template.QuestaoIntegrationDTOTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionConverterDTOTest extends BaseTest {

    @Test
    public void testFromQuestionIntegrationToQuestionDTO() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.DISCURSIVE.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.DISCURSIVE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals(questaoIntegrationDTO.getRespostaEsperada().size(), result.getExpectedAnswers().size());
    }

    @Test
    public void testFromQuestionIntegrationToQuestionDTOWhenExpectedAnswerIsNull() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.DISCURSIVE.name());
        questaoIntegrationDTO.setRespostaEsperada(null);

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.DISCURSIVE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals(null, result.getExpectedAnswers());
    }

    @Test
    public void testFromQuestionIntegrationToQuestionDTOWhenExpectedAnswerIsMultipleChoice() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.MULTIPLE_CHOICE.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.MULTIPLE_CHOICE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals(questaoIntegrationDTO.getRespostaEsperada().size(), result.getExpectedAnswers().size());
    }

    @Test
    public void testFromQuestionIntegrationToQuestionDTOWhenExpectedAnswerIsColumnBinding() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.COLUMN_BINDING.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.COLUMN_BINDING, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals(questaoIntegrationDTO.getRespostaEsperada().size(), result.getExpectedAnswers().size());
    }

    @Test
    public void testFromQuestionIntegrationToQuestionDTOWhenExpectedAnswerIsTrueOrFalse() {
        // Setup
        final QuestaoIntegrationDTO questaoIntegrationDTO = Fixture.from(QuestaoIntegrationDTO.class).gimme(QuestaoIntegrationDTOTemplate.TYPES.TRUE_OR_FALSE.name());

        // Run the test
        final QuestionDTO result = QuestionConverterDTO.fromDomainToDTO(questaoIntegrationDTO);

        // Verify the results
        assertEquals(questaoIntegrationDTO.getId(), result.getId());
        assertEquals(questaoIntegrationDTO.getEnunciado(), result.getName());
        assertEquals(QuestionType.TRUE_OR_FALSE, result.getType());
        assertEquals(null, result.getOptions());
        assertEquals(null, result.getAnswer());
        assertEquals(questaoIntegrationDTO.getRespostaEsperada().size(), result.getExpectedAnswers().size());
    }

}
