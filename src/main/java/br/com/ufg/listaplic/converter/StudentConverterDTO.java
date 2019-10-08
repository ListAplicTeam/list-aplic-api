package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.model.Student;

public final class StudentConverterDTO {

    private StudentConverterDTO() {
    }

    public static Student fromDTOToDomain(final StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        return student;
    }

    public static StudentDTO fromDomainToDTO(final Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPassword(student.getPassword());
        return studentDTO;
    }


    public static Student updateDTO(Student student, StudentDTO newStudent) {
        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());
        student.setPassword(newStudent.getPassword());
        return student;
    }
}
