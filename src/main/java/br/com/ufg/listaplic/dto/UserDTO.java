package br.com.ufg.listaplic.dto;

import br.com.ufg.listaplic.model.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.UUID;

@ApiModel(
        value = "User",
        description = "Model of a User."
)
public class UserDTO {

    @ApiModelProperty(
            value = "User identification UUID.",
            dataType = "string",
            example = "91b4a2dd-1797-48bb-8353-1231888129a1"
    )
    private UUID id;

    @ApiModelProperty(
            value = "User's name",
            dataType = "string",
            example = "Isaias Tavares da Silva Neto"
    )
    private String name;

    @ApiModelProperty(
            value = "User's email",
            dataType = "string",
            example = "isaias_neto@discente.ufg.br"
    )
    private String email;
	
    @ApiModelProperty(
            value = "User's role.",
            dataType = "string",
            example = "DISCENTE"
    )
    private Role role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
