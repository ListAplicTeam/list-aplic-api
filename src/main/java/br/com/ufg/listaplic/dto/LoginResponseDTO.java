package br.com.ufg.listaplic.dto;

public class LoginResponseDTO {

    private int statusCode;

    private StudentPublicDTO studentPublicDTO;

    public LoginResponseDTO(int statusCode, StudentPublicDTO studentPublicDTO) {
        this.statusCode = statusCode;
        this.studentPublicDTO = studentPublicDTO;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public StudentPublicDTO getStudentPublicDTO() {
        return studentPublicDTO;
    }

    public void setStudentPublicDTO(StudentPublicDTO studentPublicDTO) {
        this.studentPublicDTO = studentPublicDTO;
    }
}
