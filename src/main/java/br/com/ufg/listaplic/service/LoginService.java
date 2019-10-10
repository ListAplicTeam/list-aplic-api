package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
import br.com.ufg.listaplic.model.Role;
import br.com.ufg.listaplic.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private StudentService studentService;

    public StudentDTO authenticate(LoginDTO loginDTO) {
        StudentDTO studentDTO;

        if (loginDTO.getEmail().contains("discente")) {
            studentDTO = studentService.findByEmail(loginDTO.getEmail());
            studentDTO.setRole(Role.DISCENTE);
        } else {
            return teacherAuthentication(loginDTO);
        }

        if (!studentDTO.getPassword().equals(EncryptUtil.md5(loginDTO.getPassword()))) {
            throw new InvalidPasswordException();
        }

        return studentDTO;
    }

    private StudentDTO teacherAuthentication(LoginDTO loginDTO) {
        // MOCK enquanto a API do ListElab não está pronta
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(UUID.fromString("91b4a2dd-1797-48bb-8353-1231888129a2"));
        studentDTO.setEmail(loginDTO.getEmail());
        studentDTO.setPassword(loginDTO.getPassword());
        studentDTO.setName("Rubens Ferreira");
        studentDTO.setRole(Role.DOCENTE);
        return studentDTO;
    }
}
