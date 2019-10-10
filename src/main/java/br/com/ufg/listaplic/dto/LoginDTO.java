package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(
        value = "Login",
        description = "Model of a Login."
)
public class LoginDTO {

    @ApiModelProperty(
            value = "Student's email",
            dataType = "string",
            example = "isaias_neto@discente.ufg.br",
            required = true
    )
    @Email(message = "Invalid email")
    @Pattern(regexp = ".*@(?:discente.)?ufg.br", message = "Email must be from UFG domain")
    @NotNull(message = "email must be provided")
    private String email;

    @ApiModelProperty(
            value = "Student's password",
            dataType = "string",
            example = "nobodyyesdoor",
            required = true
    )
    @NotNull(message = "password must be provided")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
