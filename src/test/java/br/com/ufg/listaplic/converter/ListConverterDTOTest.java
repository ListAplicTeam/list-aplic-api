package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;
import br.com.ufg.listaplic.model.Answer;
import br.com.ufg.listaplic.template.ListDTOTemplate;
import br.com.ufg.listaplic.template.ListIntegrationDTOTemplate;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ListConverterDTOTest extends BaseTest {

    @Test
    public void testFromListIntegrationWithDiscursivesAndObjectivesToListsDTO() {
        // Setup
        final ListIntegrationDTO listIntegrationDTO = Fixture.from(ListIntegrationDTO.class).gimme(ListIntegrationDTOTemplate.TYPES.LIST_WITH_DISCURSIVES_AND_OBJECTIVES.name());

        // Run the test
        final ListDTO result = ListConverterDTO.fromListIntegrationToListDTO(listIntegrationDTO);

        // Verify the results
        assertEquals(listIntegrationDTO.getId(), result.getId());
        assertEquals(listIntegrationDTO.getTitulo(), result.getName());
        assertEquals(listIntegrationDTO.getUsuario(), result.getUser());
        assertEquals(listIntegrationDTO.getDisciplina().getCodigo(), result.getSubjectCode());
        assertEquals(listIntegrationDTO.getDiscursivas().size() + listIntegrationDTO.getObjetivas().size(), result.getQuestions().size());
    }

    @Test
    public void testFromListIntegrationWithDiscursivesToListsDTO() {
        // Setup
        final ListIntegrationDTO listIntegrationDTO = Fixture.from(ListIntegrationDTO.class).gimme(ListIntegrationDTOTemplate.TYPES.LIST_WITH_DISCURSIVES.name());

        // Run the test
        final ListDTO result = ListConverterDTO.fromListIntegrationToListDTO(listIntegrationDTO);

        // Verify the results
        assertEquals(listIntegrationDTO.getId(), result.getId());
        assertEquals(listIntegrationDTO.getTitulo(), result.getName());
        assertEquals(listIntegrationDTO.getUsuario(), result.getUser());
        assertEquals(listIntegrationDTO.getDisciplina().getCodigo(), result.getSubjectCode());
        assertEquals(listIntegrationDTO.getDiscursivas().size(), result.getQuestions().size());
    }

    @Test
    public void testFromListIntegrationWithObjectivesToListsDTO() {
        // Setup
        final ListIntegrationDTO listIntegrationDTO = Fixture.from(ListIntegrationDTO.class).gimme(ListIntegrationDTOTemplate.TYPES.LIST_WITH_OBJECTIVES.name());

        // Run the test
        final ListDTO result = ListConverterDTO.fromListIntegrationToListDTO(listIntegrationDTO);

        // Verify the results
        assertEquals(listIntegrationDTO.getId(), result.getId());
        assertEquals(listIntegrationDTO.getTitulo(), result.getName());
        assertEquals(listIntegrationDTO.getUsuario(), result.getUser());
        assertEquals(listIntegrationDTO.getDisciplina().getCodigo(), result.getSubjectCode());
        assertEquals(listIntegrationDTO.getObjetivas().size(), result.getQuestions().size());
    }

    @Test
    public void testFromListDTOToListAnswer() {
        // Setup
        final ListDTO listDTO = Fixture.from(ListDTO.class).gimme(ListDTOTemplate.TYPES.LIST_WITH_ONE_QUESTION.name());
        UUID userId = UUID.randomUUID();

        // Run the test
        final List<Answer> result = ListConverterDTO.fromListDTOToListAnswer(userId, listDTO);

        // Verify the results
        Answer answer = result.get(0);
        assertEquals(listDTO.getListApplicationId(), answer.getApplicationId());
        assertEquals(listDTO.getQuestions().stream().findFirst().get().getId(), answer.getQuestionId());
        assertEquals(userId, answer.getUserId());
        assertEquals(listDTO.getQuestions().stream().findFirst().get().getAnswer(), answer.getAnswer());
    }
}
