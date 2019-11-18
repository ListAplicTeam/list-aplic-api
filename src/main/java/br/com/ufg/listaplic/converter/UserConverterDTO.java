package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.InstructorDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.dto.UserDTO;
import br.com.ufg.listaplic.model.Role;

public final class UserConverterDTO {

    private UserConverterDTO() {
    }

    public static UserDTO fromStudentToUser(final StudentDTO studentDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(studentDTO.getId().toString());
        userDTO.setName(studentDTO.getName());
        userDTO.setEmail(studentDTO.getEmail());
        userDTO.setRole(Role.DISCENTE);
        return userDTO;
    }

    public static UserDTO fromInstructorToUser(final InstructorDTO instructorDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(instructorDTO.getId());
        userDTO.setName(instructorDTO.getName());
        userDTO.setEmail(instructorDTO.getEmail());
        userDTO.setRole(Role.DOCENTE);
        return userDTO;
    }

}
