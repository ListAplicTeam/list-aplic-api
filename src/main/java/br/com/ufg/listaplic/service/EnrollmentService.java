package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.dto.EnrollmentDTO;
import br.com.ufg.listaplic.exception.ResourceNotFoundException;
import br.com.ufg.listaplic.exception.StudentIsAlreadyEnrolledException;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.model.Enrollment;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.repository.EnrollmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentJpaRepository enrollmentJpaRepository;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    public void enrollment(UUID studentId, EnrollmentDTO enrollmentDTO) {
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Classroom classroom = classroomService.findByCode(enrollmentDTO.getCode());

        if (isEnrolled(classroom.getId(), student.getId())) {
            throw new StudentIsAlreadyEnrolledException();
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setClassroom(classroom);
        enrollment.setStudent(student);
        enrollmentJpaRepository.save(enrollment);
    }

    private boolean isEnrolled(UUID classroomId, UUID studentId) {
        return enrollmentJpaRepository.isEnrolled(classroomId, studentId);
    }

}
