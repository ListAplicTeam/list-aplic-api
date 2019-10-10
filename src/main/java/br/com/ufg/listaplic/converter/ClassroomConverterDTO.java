package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.model.Classroom;

public final class ClassroomConverterDTO {

    private ClassroomConverterDTO() {
    }

    public static Classroom fromDTOToDomain(final ClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setId(classroomDTO.getId());
        classroom.setName(classroomDTO.getName());
        classroom.setCode(classroomDTO.getCode());
        classroom.setSubjectCode(classroomDTO.getSubjectCode());
        classroom.setInstructorId(classroomDTO.getInstructorId());
        return classroom;
    }

    public static ClassroomDTO fromDomainToDTO(final Classroom classroom) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setId(classroom.getId());
        classroomDTO.setName(classroom.getName());
        classroomDTO.setCode(classroom.getCode());
        classroomDTO.setSubjectCode(classroom.getSubjectCode());
        classroomDTO.setInstructorId(classroom.getInstructorId());
        return classroomDTO;
    }

    public static Classroom updateDTO(Classroom classroom, ClassroomDTO classroomDTO) {
        classroom.setName(classroomDTO.getName());
        classroom.setSubjectCode(classroomDTO.getSubjectCode());
        classroom.setInstructorId(classroomDTO.getInstructorId());
        return classroom;
    }
}
