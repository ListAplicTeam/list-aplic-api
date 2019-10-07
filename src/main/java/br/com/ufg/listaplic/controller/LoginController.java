package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.LoginResponseDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.StudentPublicDTO;
import br.com.ufg.listaplic.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.HttpURLConnection;

@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public LoginResponseDTO authenticate(StudentDTO credentials) {
        StudentPublicDTO studentPublicDTO = loginService.authenticate(credentials);

        if (studentPublicDTO != null) {
            return new LoginResponseDTO(HttpURLConnection.HTTP_OK, studentPublicDTO);
        }

        return new LoginResponseDTO(HttpURLConnection.HTTP_UNAUTHORIZED, studentPublicDTO);
    }
}
