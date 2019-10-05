package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.service.StudentService;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentControllerTest extends BaseTest {

    @InjectMocks
    private StudentController studentControllerUnderTest;

    @Mock
    private StudentService mockStudentService;

    @Test
    public void testFindAll() {
        // Setup
        final List<StudentDTO> studentsDTO = Fixture.from(StudentDTO.class).gimme(2, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentService.findAll()).thenReturn(studentsDTO);

        // Run the test
        final List<StudentDTO> result = studentControllerUnderTest.findAll();

        // Verify the results
        assertEquals(studentsDTO.size(), result.size());
        assertEquals(studentsDTO, result);
    }

    @Test
    public void testFindById() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentService.findById(any(UUID.class))).thenReturn(studentDTO);

        // Run the test
        final StudentDTO result = studentControllerUnderTest.findById(UUID.randomUUID());

        // Verify the results
        assertEquals(studentDTO, result);
    }

    @Test
    public void testSave() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT.name());
        when(mockStudentService.save(studentDTO)).thenReturn(studentDTO);

        // Run the test
        final StudentDTO result = studentControllerUnderTest.save(studentDTO);

        // Verify the results
        assertEquals(studentDTO, result);
    }

    @Test
    public void testUpdate() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentService.update(any(UUID.class), any(StudentDTO.class))).thenReturn(studentDTO);

        // Run the test
        final StudentDTO result = studentControllerUnderTest.update(UUID.randomUUID(), studentDTO);

        // Verify the results
        assertEquals(studentDTO, result);
    }

    @Test
    public void testDeleteById() {
        // Setup
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentService.findStudentById(any(UUID.class))).thenReturn(Optional.of(student));

        // Run the test
        final ResponseEntity result = studentControllerUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        assertEquals(ResponseEntity.ok().build(), result);
        verify(mockStudentService, times(1)).deleteById(any(UUID.class));
    }

    @Test
    public void testDeleteByIdShouldReturnStatusNotFoundWhenStudentNotFound() {
        // Setup
        when(mockStudentService.findStudentById(any(UUID.class))).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity result = studentControllerUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        assertEquals(ResponseEntity.notFound().build(), result);
        verify(mockStudentService, times(0)).deleteById(any(UUID.class));
    }
}
