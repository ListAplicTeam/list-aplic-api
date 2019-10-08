package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
import br.com.ufg.listaplic.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private StudentService studentService;

    public StudentDTO authenticate(LoginDTO loginDTO) {
        StudentDTO studentDTO = studentService.findByEmail(loginDTO.getEmail());

        if (!studentDTO.getPassword().equals(EncryptUtil.md5(loginDTO.getPassword()))) {
            throw new InvalidPasswordException();
        }

        return studentDTO;
    }

}
