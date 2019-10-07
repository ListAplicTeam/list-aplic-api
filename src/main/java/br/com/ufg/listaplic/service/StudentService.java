package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.StudentConverterDTO;
import br.com.ufg.listaplic.dto.LoginResponseDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.exception.StudentNotFoundException;
import br.com.ufg.listaplic.model.Student;
import br.com.ufg.listaplic.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    public List<StudentDTO> findAll() {
        return studentJpaRepository.findAll()
                .stream()
                .map(StudentConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(UUID id) {
        return studentJpaRepository.findById(id)
                .map(StudentConverterDTO::fromDomainToDTO)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public StudentDTO findByEmail(String email) {
        return studentJpaRepository.findByEmail(email)
                .map(StudentConverterDTO::fromDomainToDTO)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public Optional<Student> findStudentById(UUID id) {
        return studentJpaRepository.findById(id);
    }

    public StudentDTO save(StudentDTO studentDTO) {
        Student student = StudentConverterDTO.fromDTOToDomain(studentDTO);
        student.setPassword(LoginService.md5(student.getPassword()));
        Student studentSaved = studentJpaRepository.save(student);
        return StudentConverterDTO.fromDomainToDTO(studentSaved);
    }

    public StudentDTO update(UUID id, StudentDTO newStudentDTO) {
        Student student = studentJpaRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Student newStudent = StudentConverterDTO.updateDTO(student, newStudentDTO);
        Student studentSaved = studentJpaRepository.save(newStudent);
        return StudentConverterDTO.fromDomainToDTO(studentSaved);
    }

    public void deleteById(UUID id) {
        studentJpaRepository.deleteById(id);
    }
}
