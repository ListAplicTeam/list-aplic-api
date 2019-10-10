package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            response = UserDTO.class
    )
    @ApiResponse(
            code = 201,
            message = "Logged in successfully.",
            response = UserDTO.class
    )
    @PostMapping
    public UserDTO authenticate(@RequestBody @Valid LoginDTO loginDTO) {
        return loginService.authenticate(loginDTO);
    }
}
