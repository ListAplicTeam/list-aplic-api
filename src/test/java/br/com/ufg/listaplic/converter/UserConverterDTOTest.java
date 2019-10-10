package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.InstructorDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.model.Role;
import br.com.ufg.listaplic.template.InstructorDTOTemplate;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import br.com.ufg.listaplic.template.UserDTOTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConverterDTOTest extends BaseTest {

    @Test
    public void testFromStudentToUser() {
        // Setup
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());
        final UserDTO userDTO = Fixture.from(UserDTO.class).gimme(UserDTOTemplate.TYPES.USER.name());

        // Run the test
        final UserDTO result = UserConverterDTO.fromStudentToUser(studentDTO);

        // Verify the results
        assertEquals(studentDTO.getName(), result.getName());
        assertEquals(studentDTO.getEmail(), result.getEmail());
        assertEquals(Role.DISCENTE, result.getRole());
    }

    @Test
    public void testFromInstructorToUser() {
        // Setup
        final InstructorDTO instructorDTO = Fixture.from(InstructorDTO.class).gimme(InstructorDTOTemplate.TYPES.INSTRUCTOR.name());
        final UserDTO userDTO = Fixture.from(UserDTO.class).gimme(UserDTOTemplate.TYPES.USER.name());

        // Run the test
        final UserDTO result = UserConverterDTO.fromInstructorToUser(instructorDTO);

        // Verify the results
        assertEquals(instructorDTO.getName(), result.getName());
        assertEquals(instructorDTO.getEmail(), result.getEmail());
        assertEquals(Role.DOCENTE, result.getRole());
    }
}
