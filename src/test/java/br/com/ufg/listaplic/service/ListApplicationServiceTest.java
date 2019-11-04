package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.ListApplication;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import br.com.ufg.listaplic.template.ClassroomTemplate;
import br.com.ufg.listaplic.template.ListApplicationTemplate;
import br.com.ufg.listaplic.template.StudentTemplate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ListApplicationServiceTest extends BaseTest {

    @InjectMocks
    private ListApplicationService listApplicationServiceUnderTest;

    @Mock
    private ListApplicationJpaRepository mockListApplicationJpaRepository;
    @Mock
    private ClassroomJpaRepository mockClassroomJpaRepository;
    @Mock
    private StudentJpaRepository mockStudentJpaRepository;

    @Test
    public void testGetFinishedListsByClassroomId() {
        // Setup
        final Classroom classroom = Fixture.from(Classroom.class).gimme(ClassroomTemplate.TYPES.CLASSROOM_WITH_ID.name());
        final List<Student> students = Fixture.from(Student.class).gimme(1, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        final List<ListApplication> applications = Fixture.from(ListApplication.class).gimme(2, ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
        when(mockClassroomJpaRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(classroom));
        when(mockListApplicationJpaRepository.findByClassroomAndStatus(any(Classroom.class), any(ApplicationListStatus.class))).thenReturn(applications);
        when(mockStudentJpaRepository.findStudentsByClassroomId(any(UUID.class))).thenReturn(students);

        // Run the Test
        final List<ListApplicationDTO> result = listApplicationServiceUnderTest.getFinishedListsByClassroomId(classroom.getId());

        // Verify the results
        assertEquals(applications.size(), result.size());
    }
}
