package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.dto.SubjectType;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubjectServiceTest extends BaseTest {

    @InjectMocks
    private SubjectService subjectServiceUnderTest;

    @Test
    public void testFindAll() {
        // Run the test
        List<SubjectDTO> results = subjectServiceUnderTest.findAll();

        // Verify the results
        assertEquals(SubjectType.values().length, results.size());
    }

}
