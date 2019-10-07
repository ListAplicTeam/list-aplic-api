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
            value = "Student identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1",
            readOnly = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "Student's name",
            dataType = "string",
            example = "Isaias Tavares da Silva Neto",
            required = true
    )
    @NotNull(message = "name must be provided")
    private String name;

    @ApiModelProperty(
            value = "Student's email",
            dataType = "string",
            example = "isaias_neto@discente.ufg.br",
            required = true
    )
    @Email(message = "Invalid email")
    @Pattern(regexp = ".*@discente.ufg.br", message = "Email must be from UFG domain")
    @NotNull(message = "email must be provided")
    private String email;

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
