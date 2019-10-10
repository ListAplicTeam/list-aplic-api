package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import br.com.ufg.listaplic.template.ClassroomDTOTemplate;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClassroomServiceTest extends BaseTest {

    @InjectMocks
    private ClassroomService classroomServiceUnderTest;

    @Mock
    private ClassroomJpaRepository mockClassroomJpaRepository;

    @Test
    public void testFindAll() {
        // Setup
        final List<Classroom> classrooms = Fixture.from(Classroom.class).gimme(2, ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.findAll()).thenReturn(classrooms);

        // Run the test
        final List<ClassroomDTO> result = classroomServiceUnderTest.findAll();

        // Verify the results
        assertEquals(classrooms.size(), result.size());
    }

    @Test
    public void testFindById() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(classroom));

        // Run the test
        final ClassroomDTO result = classroomServiceUnderTest.findById(UUID.randomUUID());

        // Verify the results
        assertEquals(classroomDTO.getName(), result.getName());
        assertEquals(classroomDTO.getCode(), result.getCode());
        assertEquals(classroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testFindClassroomById() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(classroom));

        // Run the test
        final Optional<Classroom> result = classroomServiceUnderTest.findClassroomById(UUID.randomUUID());

        // Verify the results
        assertEquals(classroom, result.get());
        assertEquals(classroom.getName(), result.get().getName());
        assertEquals(classroom.getCode(), result.get().getCode());
        assertEquals(classroom.getSubjectCode(), result.get().getSubjectCode());
        assertEquals(classroom.getInstructorId(), result.get().getInstructorId());
    }

    @Test
    public void testFindClassroomByCode() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.findByCode(anyString())).thenReturn(Optional.of(classroom));

        // Run the test
        final Classroom result = classroomServiceUnderTest.findByCode(classroom.getCode());

        // Verify the results
        assertEquals(classroom, result);
        assertEquals(classroom.getName(), result.getName());
        assertEquals(classroom.getCode(), result.getCode());
        assertEquals(classroom.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroom.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testSave() {
        // Setup
        final ClassroomDTO classroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.save(any(Classroom.class))).thenReturn(classroom);

        // Run the test
        final ClassroomDTO result = classroomServiceUnderTest.save(classroomDTO);

        // Verify the results
        assertNotNull(result.getCode());
        assertEquals(classroomDTO.getName(), result.getName());
        assertEquals(classroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(classroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testUpdate() {
        // Setup
        final ClassroomDTO newClassroomDTO = Fixture.from(ClassroomDTO.class).gimme(ClassroomDTOTemplate.TYPES.CLASSROOM.name());
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(Optional.of(classroom));
        when(mockClassroomJpaRepository.save(classroom)).thenReturn(classroom);

        // Run the test
        final ClassroomDTO result = classroomServiceUnderTest.update(UUID.randomUUID(), newClassroomDTO);

        // Verify the results
        assertEquals(newClassroomDTO.getName(), result.getName());
        assertEquals(newClassroomDTO.getSubjectCode(), result.getSubjectCode());
        assertEquals(newClassroomDTO.getInstructorId(), result.getInstructorId());
    }

    @Test
    public void testDeleteById() {
        // Setup
        Mockito.doNothing().when(mockClassroomJpaRepository).deleteById(any(UUID.class));

        // Run the test
        classroomServiceUnderTest.deleteById(UUID.randomUUID());

        // Verify the results
        verify(mockClassroomJpaRepository, times(1)).deleteById(any(UUID.class));
    }
}
