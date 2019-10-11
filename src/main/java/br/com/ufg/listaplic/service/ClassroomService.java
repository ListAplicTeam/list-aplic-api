package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.exception.ResourceNotFoundException;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    private static final String CLASSROOM_NOT_FOUND = "Classroom not found";

    @Autowired
    private ClassroomJpaRepository classroomJpaRepository;

    public List<ClassroomDTO> findAll() {
        return classroomJpaRepository.findAll()
                .stream()
                .map(ClassroomConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());
    }

    public ClassroomDTO findById(UUID id) {
        return classroomJpaRepository.findById(id)
                .map(ClassroomConverterDTO::fromDomainToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(CLASSROOM_NOT_FOUND));
    }

    public List<ClassroomDTO> findByStudentId(UUID studentId) {
        return classroomJpaRepository.findByStudentId(studentId)
                .stream()
                .map(ClassroomConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());
    }

    public List<ClassroomDTO> findByInstructorId(UUID instructorId) {
        return classroomJpaRepository.findByInstructorId(instructorId)
                .stream()
                .map(ClassroomConverterDTO::fromDomainToDTO)
                .collect(Collectors.toList());
    }

    public Classroom findByCode(String code) {
        return classroomJpaRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(CLASSROOM_NOT_FOUND));
    }

    public Optional<Classroom> findClassroomById(UUID id) {
        return classroomJpaRepository.findById(id);
    }

    public ClassroomDTO save(ClassroomDTO classroomDTO) {
        classroomDTO.setCode(generateClassroomCode());
        Classroom classroom = ClassroomConverterDTO.fromDTOToDomain(classroomDTO);
        Classroom classroomSaved = classroomJpaRepository.save(classroom);
        return ClassroomConverterDTO.fromDomainToDTO(classroomSaved);
    }

    public ClassroomDTO update(UUID id, ClassroomDTO newClassroomDTO) {
        Classroom classroom = classroomJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLASSROOM_NOT_FOUND));

        Classroom newClassroom = ClassroomConverterDTO.updateDTO(classroom, newClassroomDTO);
        Classroom classroomSaved = classroomJpaRepository.save(newClassroom);
        return ClassroomConverterDTO.fromDomainToDTO(classroomSaved);
    }

    public void deleteById(UUID id) {
        classroomJpaRepository.deleteById(id);
    }

    private static String generateClassroomCode() {
        final int stringSize = 3;
        final int numberSize = 4;
        String letters = RandomStringUtils.randomAlphabetic(stringSize).toUpperCase();
        String numbers = RandomStringUtils.randomNumeric(numberSize);
        return letters.concat(numbers);
    }

}
