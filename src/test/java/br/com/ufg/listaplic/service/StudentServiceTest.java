package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest extends BaseTest {

    @InjectMocks
    private StudentService studentServiceUnderTest;

    @Mock
    private StudentJpaRepository mockStudentJpaRepository;

    @Test
    public void testFindAll() {
        // Setup
        final List<StudentDTO> studentsDTO = Fixture.from(StudentDTO.class).gimme(2, StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        final List<Student> students = Fixture.from(Student.class).gimme(2, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentJpaRepository.findAll()).thenReturn(students);

        // Run the test
        final List<StudentDTO> result = studentServiceUnderTest.findAll();

        // Verify the results
        assertEquals(students.size(), result.size());
        assertEquals(studentsDTO, result);
    }

    @Test
    public void testFindById() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(student));

        // Run the test
        final StudentDTO result = studentServiceUnderTest.findById(UUID.randomUUID());

        // Verify the results
        assertEquals(studentDTO, result);
    }

    @Test
    public void testFindStudentById() {
        // Setup
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(student));

        // Run the test
        final Optional<Student> result = studentServiceUnderTest.findStudentById(UUID.randomUUID());

        // Verify the results
        assertEquals(student, result.get());
    }

    @Test
    public void testSave() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentJpaRepository.save(any(Student.class))).thenReturn(student);

        // Run the test
        final StudentDTO result = studentServiceUnderTest.save(studentDTO);

        // Verify the results
        assertEquals(studentDTO, result);
    }

    @Test
    public void testUpdate() {
        // Setup
        final StudentDTO newStudentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT.name());
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockStudentJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(student));
        when(mockStudentJpaRepository.save(student)).thenReturn(student);

        // Run the test
        final StudentDTO result = studentServiceUnderTest.update(UUID.randomUUID(), newStudentDTO);

        // Verify the results
        assertEquals(newStudentDTO, result);
    }

    @Test
    public void testDeleteById() {
        // Setup
        Mockito.doNothing().when(mockStudentJpaRepository).deleteById(any(UUID.class));

        // Run the test
        studentServiceUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        verify(mockStudentJpaRepository, times(1)).deleteById(any(UUID.class));
    }
}
