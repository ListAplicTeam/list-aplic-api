package br.com.ufg.listaplic.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassroomConverterDTOTest extends BaseTest {

    @Test
    public void whenConvertingClassroomDTO_thenSuccess() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());

        final Classroom classroomConverted = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);

        // Verify the results
        assertEquals(classroomDTO.getId(), classroomConverted.getId());
        assertEquals(classroomDTO.getName(), classroomConverted.getName());
        assertEquals(classroomDTO.getSubjectCode(), classroomConverted.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), classroomConverted.getInstructorId());
    }

    @Test
    public void whenConvertingClassroom_thenSuccess() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());

        final ClassroomDTO classroomDTOConverted = ClassroomConverterDTO.fromDomainToDTO(classroom);

        // Verify the results
        assertEquals(classroom.getId(), classroomDTOConverted.getId());
        assertEquals(classroom.getName(), classroomDTOConverted.getName());
        assertEquals(classroom.getSubjectCode(), classroomDTOConverted.getSubjectCode());
        assertEquals(classroom.getInstructorId(), classroomDTOConverted.getInstructorId());
    }

    @Test
    public void whenUpdateDTO_thenSuccess() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.ANOTHER_CLASSROOM.name());

        // Run the test
        final Classroom newClassroom = ClassroomConverterDTO.updateDTO(classroom, classroomDTO);

        // Verify the results
        assertEquals(classroomDTO.getName(), newClassroom.getName());
        assertEquals(classroomDTO.getSubjectCode(), newClassroom.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), newClassroom.getInstructorId());
    }

}
