package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.InstructorDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
import br.com.ufg.listaplic.model.Role;
import br.com.ufg.listaplic.template.InstructorDTOTemplate;
import br.com.ufg.listaplic.template.LoginDTOTemplate;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginServiceTest extends BaseTest {

    @InjectMocks
    private LoginService loginServiceUnderTest;

    @Mock
    private StudentService mockStudentService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testAuthenticateWithStudent() {
        // Setup
        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_STUDENT.name());
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());

        when(mockStudentService.findByEmail(anyString())).thenReturn(studentDTO);

        // Run the test
        final UserDTO result = loginServiceUnderTest.authenticate(loginDTO);

        // Verify the results
        assertEquals(studentDTO.getId(), result.getId());
        assertEquals(studentDTO.getName(), result.getName());
        assertEquals(studentDTO.getEmail(), result.getEmail());
        assertEquals(Role.DISCENTE, result.getRole());
    }

    @Test
    public void testAuthenticateWithInstructor() {
        // Setup
        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_INSTRUCTOR.name());
        final InstructorDTO instructorDTO = Fixture.from(InstructorDTO.class).gimme(InstructorDTOTemplate.TYPES.INSTRUCTOR.name());

        // Run the test
        final UserDTO result = loginServiceUnderTest.authenticate(loginDTO);

        // Verify the results
        assertEquals(instructorDTO.getId(), result.getId());
        assertEquals(instructorDTO.getName(), result.getName());
        assertEquals(instructorDTO.getEmail(), result.getEmail());
        assertEquals(Role.DOCENTE, result.getRole());
    }

    @Test
    public void testAuthenticateWhenFailed() {
        // Setup
        exceptionRule.expect(InvalidPasswordException.class);

        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_STUDENT.name());
        loginDTO.setPassword("wrong_password");
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());

        when(mockStudentService.findByEmail(anyString())).thenReturn(studentDTO);

        // Run the test
        loginServiceUnderTest.authenticate(loginDTO);
    }
}
