package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentConverterDTOTest extends BaseTest {

    @Test
    public void whenConvertingStudentDTO_thenSuccess() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());

        final Student studentConverted = StudentConverterDTO.fromDTOToDomain(studentDTO);

        // Verify the results
        assertEquals(studentDTO.getId(), studentConverted.getId());
        assertEquals(studentDTO.getName(), studentConverted.getName());
        assertEquals(studentDTO.getEmail(), studentConverted.getEmail());
        assertEquals(studentDTO.getPassword(), studentConverted.getPassword());
    }

    @Test
    public void whenConvertingStudent_thenSuccess() {
        // Setup
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());

        final StudentDTO studentDTOConverted = StudentConverterDTO.fromDomainToDTO(student);

        // Verify the results
        assertEquals(student.getId(), studentDTOConverted.getId());
        assertEquals(student.getName(), studentDTOConverted.getName());
        assertEquals(student.getEmail(), studentDTOConverted.getEmail());
        assertEquals(student.getPassword(), studentDTOConverted.getPassword());
    }

    @Test
    public void whenUpdateDTO_thenSuccess() {
        // Setup
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.ANOTHER_STUDENT.name());

        // Run the test
        final Student newStudent = StudentConverterDTO.updateDTO(student, studentDTO);

        // Verify the results
        assertEquals(studentDTO.getName(), newStudent.getName());
        assertEquals(studentDTO.getEmail(), newStudent.getEmail());
        assertEquals(studentDTO.getPassword(), newStudent.getPassword());
    }

}
