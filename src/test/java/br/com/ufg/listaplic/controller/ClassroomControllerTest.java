package br.com.ufg.listaplic.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.service.ClassroomService;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClassroomControllerTest extends BaseTest {

    @InjectMocks
    private ClassroomController classroomControllerUnderTest;

    @Mock
    private ClassroomService mockClassroomService;

    @Test
    public void testFindAll() {
        // Setup
        final List<ClassroomDTO> classroomDTOS = Fixture.from(ClassroomDTO.class).gimme(2, ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomService.findAll()).thenReturn(classroomDTOS);

        // Run the test
        final List<ClassroomDTO> result = classroomControllerUnderTest.findAll();

        // Verify the results
        assertEquals(classroomDTOS.size(), result.size());
    }

    @Test
    public void testFindById() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomService.findById(any(UUID.class))).thenReturn(classroomDTO);

        // Run the test
        final ClassroomDTO result = classroomControllerUnderTest.findById(UUID.randomUUID());

        // Verify the results
        assertEquals(classroomDTO.getName(), result.getName());
        assertEquals(classroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testSave() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM.name());
        when(mockClassroomService.save(classroomDTO)).thenReturn(classroomDTO);

        // Run the test
        final ClassroomDTO result = classroomControllerUnderTest.save(classroomDTO);

        // Verify the results
        assertEquals(classroomDTO.getName(), result.getName());
        assertEquals(classroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testUpdate() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomService.update(any(UUID.class), any(ClassroomDTO.class))).thenReturn(classroomDTO);

        // Run the test
        final ClassroomDTO result = classroomControllerUnderTest.update(UUID.randomUUID(), classroomDTO);

        // Verify the results
        assertEquals(classroomDTO.getName(), result.getName());
        assertEquals(classroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testDeleteById() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.of(classroom));

        // Run the test
        final ResponseEntity result = classroomControllerUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        assertEquals(ResponseEntity.ok().build(), result);
        verify(mockClassroomService, times(1)).deleteById(any(UUID.class));
    }

    @Test
    public void testDeleteByIdShouldReturnStatusNotFoundWhenClassroomNotFound() {
        // Setup
        when(mockClassroomService.findClassroomById(any(UUID.class))).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity result = classroomControllerUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        assertEquals(ResponseEntity.notFound().build(), result);
        verify(mockClassroomService, times(0)).deleteById(any(UUID.class));
    }
}
