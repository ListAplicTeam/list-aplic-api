package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.UserConverterDTO;
import br.com.ufg.listaplic.dto.InstructorDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.exception.InvalidPasswordException;
import br.com.ufg.listaplic.model.Role;
import br.com.ufg.listaplic.network.ListElabNetwork;
import br.com.ufg.listaplic.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ListElabNetwork listElabNetwork;

    public UserDTO authenticate(LoginDTO loginDTO) {
        if (loginDTO.getEmail().contains(Role.DISCENTE.name().toLowerCase())) {
            StudentDTO studentDTO = studentService.findByEmail(loginDTO.getEmail());

            if (!studentDTO.getPassword().equals(EncryptUtil.md5(loginDTO.getPassword()))) {
                throw new InvalidPasswordException();
            }

            return UserConverterDTO.fromStudentToUser(studentDTO);
        } else {
            InstructorDTO instructorDTO = instructorAuthentication(loginDTO);
            return UserConverterDTO.fromInstructorToUser(instructorDTO);
        }
    }

    private InstructorDTO instructorAuthentication(LoginDTO loginDTO) {
        listElabNetwork.login(loginDTO);
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setEmail(loginDTO.getEmail());
        return instructorDTO;
    }
}
