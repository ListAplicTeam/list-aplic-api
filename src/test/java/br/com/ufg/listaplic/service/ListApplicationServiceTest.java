package br.com.ufg.listaplic.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.ufg.listaplic.base.BaseTest;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.model.*;
import br.com.ufg.listaplic.repository.AnswerJpaRepository;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import br.com.ufg.listaplic.repository.ListApplicationJpaRepository;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import br.com.ufg.listaplic.template.AnswerTemplate;
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
    @Mock
    private AnswerJpaRepository mockAnswerJpaRepository;

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

    @Test
    public void testGetListApplicationDetail() {
        // Setup
        ListApplication application = Fixture.from(ListApplication.class).gimme(ListApplicationTemplate.TYPES.FINISHED_APPLICATION.name());
        List<Answer> answers = Fixture.from(Answer.class).gimme(2, AnswerTemplate.TYPES.ANSWER.name());
        List<Student> students = Fixture.from(Student.class).gimme(1, StudentTemplate.TYPES.STUDENT_WITH_ID.name());
        when(mockListApplicationJpaRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(application));
        when(mockAnswerJpaRepository.findByApplicationId(any(UUID.class))).thenReturn(answers);
        when(mockStudentJpaRepository.findStudentsByClassroomId(any(UUID.class))).thenReturn(students);

        // Run the Test
        final ListApplicationDTO result = listApplicationServiceUnderTest.getListApplicationDetail(application.getId());

        // Verify the result
        assertEquals(application.getId(), result.getId());
        assertEquals(application.getList(), result.getListId());
        assertEquals(application.getClassroom().getId(), result.getGroupId());
        assertEquals(answers.size(), result.getAnswerList().size());
    }
}
