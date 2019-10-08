package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Api(value = "Login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(
            value = "Login",
            response = StudentDTO.class
    )
    @ApiResponse(
            code = 201,
            message = "Logged in successfully.",
            response = StudentDTO.class
    )
    @PostMapping
    public StudentDTO authenticate(@Valid LoginDTO loginDTO) {
        return loginService.authenticate(loginDTO);
    }
}
