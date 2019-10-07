package br.com.ufg.listaplic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.UUID;

@ApiModel(
        value = "Student",
        description = "Model of a Student."
)
public class StudentDTO extends StudentPublicDTO {
	
	@ApiModelProperty(
            value = "Student's password",
            dataType = "string",
            example = "nobodyyesdoor",
            required = true
    )
    @NotNull(message = "password must be provided")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
