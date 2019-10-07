package br.com.ufg.listaplic.service;

import br.com.ufg.listaplic.converter.ClassroomConverterDTO;
import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.exception.ResourceNotFoundException;
import br.com.ufg.listaplic.model.Classroom;
import br.com.ufg.listaplic.repository.ClassroomJpaRepository;
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

    public Classroom findBySubjectCode(String subjectCode) {
        return classroomJpaRepository.findBySubjectCode(subjectCode)
                .orElseThrow(() -> new ResourceNotFoundException(CLASSROOM_NOT_FOUND));
    }

    public Optional<Classroom> findClassroomById(UUID id) {
        return classroomJpaRepository.findById(id);
    }

    public ClassroomDTO save(ClassroomDTO classroomDTO) {
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

}
