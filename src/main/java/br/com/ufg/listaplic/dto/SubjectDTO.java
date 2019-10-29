package br.com.ufg.listaplic.dto;

public class SubjectDTO {

    private String name;
    private String code;

    public SubjectDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
