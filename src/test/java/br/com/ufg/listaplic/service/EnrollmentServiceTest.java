package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.EnrollmentDTO;
import br.com.ufg.listaplic.exception.StudentIsAlreadyEnrolledException;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.Enrollment;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.repository.EnrollmentJpaRepository;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import br.com.ufg.listaplic.template.EnrollmentDTOTemplate;
import br.com.ufg.listaplic.template.EnrollmentTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnrollmentServiceTest extends BaseTest {

    @InjectMocks
    private EnrollmentService enrollmentServiceUnderTest;

    @Mock
    private EnrollmentJpaRepository mockEnrollmentJpaRepository;
    @Mock
    private ClassroomService mockClassroomService;
    @Mock
    private StudentService mockStudentService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testEnrollment() {
        // Setup
        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final Enrollment enrollment = Fixture.from(Enrollment.class).gimme(EnrollmentTemplate.TYPES.ENROLLMENT.name());
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());

        when(mockStudentService.findStudentById(any(UUID.class))).thenReturn(Optional.of(student));
        when(mockClassroomService.findByCode(anyString())).thenReturn(classroom);
        when(mockEnrollmentJpaRepository.save(any(Enrollment.class))).thenReturn(enrollment);
        when(mockEnrollmentJpaRepository.isEnrolled(any(UUID.class), any(UUID.class))).thenReturn(Boolean.FALSE);

        // Run the test
        enrollmentServiceUnderTest.enrollment(UUID.randomUUID(), enrollmentDTO);

        // Verify the results
        verify(mockStudentService, times(1)).findStudentById(any(UUID.class));
        verify(mockClassroomService, times(1)).findByCode(anyString());
        verify(mockEnrollmentJpaRepository, times(1)).isEnrolled(any(UUID.class), any(UUID.class));
        verify(mockEnrollmentJpaRepository, times(1)).save(any(Enrollment.class));
    }

    @Test
    public void testWhenStudentIsAlreadyEnrolled() {
        // Setup
        exceptionRule.expect(StudentIsAlreadyEnrolledException.class);

        final Student student = Fixture.from(Student.class).gimme(StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final EnrollmentDTO enrollmentDTO = Fixture.from(EnrollmentDTO.class).gimme(EnrollmentDTOTemplate.TYPES.ENROLLMENT.name());

        when(mockStudentService.findStudentById(any(UUID.class))).thenReturn(Optional.of(student));
        when(mockClassroomService.findByCode(anyString())).thenReturn(classroom);
        when(mockEnrollmentJpaRepository.isEnrolled(any(UUID.class), any(UUID.class))).thenReturn(Boolean.TRUE);

        // Run the test
        enrollmentServiceUnderTest.enrollment(UUID.randomUUID(), enrollmentDTO);

        // Verify the results
        verify(mockStudentService, times(1)).findStudentById(any(UUID.class));
        verify(mockClassroomService, times(1)).findByCode(anyString());
        verify(mockEnrollmentJpaRepository, times(1)).isEnrolled(any(UUID.class), any(UUID.class));
        verify(mockEnrollmentJpaRepository, times(0)).save(any(Enrollment.class));
    }
}
