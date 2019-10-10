package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
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
    public void testAuthenticate() {
        // Setup
        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_DTO.name());
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());

        when(mockStudentService.findByEmail(anyString())).thenReturn(studentDTO);

        // Run the test
        final StudentDTO result = loginServiceUnderTest.authenticate(loginDTO);

        // Verify the results
        assertEquals(studentDTO.getEmail(), result.getEmail());
    }

    @Test
    public void testAuthenticateWhenFailed() {
        // Setup
        exceptionRule.expect(InvalidPasswordException.class);

        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_DTO.name());
        loginDTO.setPassword("wrong_password");
        final StudentDTO studentDTO = Fixture.from(StudentDTO.class).gimme(StudentDTOTemplate.TYPES.STUDENT_WITH_ID.name());

        when(mockStudentService.findByEmail(anyString())).thenReturn(studentDTO);

        // Run the test
        final StudentDTO result = loginServiceUnderTest.authenticate(loginDTO);
    }
}
