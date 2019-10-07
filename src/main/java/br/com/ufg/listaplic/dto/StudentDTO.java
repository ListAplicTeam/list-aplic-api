package br.com.ufg.listaplic.dto;

import java.util.UUID;

public class StudentDTO extends StudentPublicDTO {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
