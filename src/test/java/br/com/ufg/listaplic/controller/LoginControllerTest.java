package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.service.LoginService;
import br.com.ufg.listaplic.template.LoginDTOTemplate;
import br.com.ufg.listaplic.template.UserDTOTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoginControllerTest extends BaseTest {

    @InjectMocks
    private LoginController loginControllerUnderTest;

    @Mock
    private LoginService mockLoginService;

    @Test
    public void testAuthenticate() {
        // Setup
        final LoginDTO loginDTO = Fixture.from(LoginDTO.class).gimme(LoginDTOTemplate.TYPES.LOGIN_DTO.name());
        final UserDTO userDTO = Fixture.from(UserDTO.class).gimme(UserDTOTemplate.TYPES.USER.name());

        when(mockLoginService.authenticate(any(LoginDTO.class))).thenReturn(userDTO);

        // Run the test
        final UserDTO result = loginControllerUnderTest.authenticate(loginDTO);

        // Verify the results
        assertEquals(userDTO.getEmail(), result.getEmail());
    }
}
