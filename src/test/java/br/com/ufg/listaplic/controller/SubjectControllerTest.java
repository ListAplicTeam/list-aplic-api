package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.service.SubjectService;
import br.com.ufg.listaplic.template.SubjectDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SubjectControllerTest extends BaseTest {

    @InjectMocks
    private SubjectController subjectControllerUnderTest;

    @Mock
    private SubjectService mockSubjectService;

    @Test
    public void testFindAll() {
        // Setup
        final List<SubjectDTO> subjectDTOS = Fixture.from(SubjectDTO.class).gimme(2, SubjectDTOTemplate.TYPES.SUBJECT_INF0233.name());
        when(mockSubjectService.findAll()).thenReturn(subjectDTOS);

        // Run the test
        final List<SubjectDTO> result = subjectControllerUnderTest.findAll();

        // Verify the results
        assertEquals(subjectDTOS.size(), result.size());
    }

}
