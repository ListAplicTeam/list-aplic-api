package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.dto.listelab.UserIntegrationDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
import br.com.ufg.listaplic.model.Role;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.template.LoginDTOTemplate;
import br.com.ufg.listaplic.template.StudentDTOTemplate;
import br.com.ufg.listaplic.template.UserIntegrationDTOTemplate;
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

    @Mock
    private ListElabNetwork mockListElabNetwork;

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
        assertEquals(studentDTO.getId().toString(), result.getId());
        assertEquals(studentDTO.getName(), result.getName());
        assertEquals(studentDTO.getEmail(), result.getEmail());
        assertEquals(Role.DISCENTE, result.getRole());
    }

    @Test
    public void testAuthenticateWithInstructor() {
        // Setup
        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_INSTRUCTOR.name());
        final UserIntegrationDTO userIntegrationDTO = Fixture.from(UserIntegrationDTO.class).gimme(UserIntegrationDTOTemplate.TYPES.USER.name());

        when(mockListElabNetwork.login(loginDTO)).thenReturn(userIntegrationDTO);

        // Run the test
        final UserDTO result = loginServiceUnderTest.authenticate(loginDTO);

        // Verify the results
        assertEquals(userIntegrationDTO.getId(), result.getId());
        assertEquals(userIntegrationDTO.getName(), result.getName());
        assertEquals(userIntegrationDTO.getEmail(), result.getEmail());
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
