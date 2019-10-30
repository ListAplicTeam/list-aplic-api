package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.ClassroomDTO;
import br.com.ufg.listaplic.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/classrooms")
@Api(value = "Classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @ApiOperation(
            value = "Get All Classrooms",
            responseContainer = "list",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get All Classrooms",
            responseContainer = "list",
            response = ClassroomDTO.class
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDTO> findAll() {
        return classroomService.findAll();
    }

    @ApiOperation(
            value = "Get Classroom by id",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get Classroom by id",
            response = ClassroomDTO.class
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDTO findById(@PathVariable("id") UUID id) {
        return classroomService.findById(id);
    }

    @ApiOperation(
            value = "Get Classrooms by student id",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get Classrooms by student id",
            responseContainer = "list",
            response = ClassroomDTO.class
    )
    @GetMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDTO> findByStudentId(@RequestParam("studentId") UUID studentId) {
        return classroomService.findByStudentId(studentId);
    }

    @ApiOperation(
            value = "Get Classrooms by instructor id",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get Classrooms by instructor id",
            responseContainer = "list",
            response = ClassroomDTO.class
    )
    @GetMapping("/instructor")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDTO> findByInstructorId(@RequestParam("instructorId") String instructorId) {
        return classroomService.findByInstructorId(instructorId);
    }

    @ApiOperation(
            value = "Create Classroom",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 201,
            message = "Classroom created successfully.",
            response = ClassroomDTO.class
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassroomDTO save(@RequestBody @Valid ClassroomDTO classroomDTO) {
        return classroomService.save(classroomDTO);
    }

    @ApiOperation(
            value = "Update Classroom",
            response = ClassroomDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Classroom updated successfully",
            response = ClassroomDTO.class
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDTO update(@PathVariable("id") UUID id,
                               @RequestBody @Valid ClassroomDTO classroomDTO) {
        return classroomService.update(id, classroomDTO);
    }

    @ApiOperation(
            value = "Delete Classroom by id"
    )
    @ApiResponse(
            code = 200,
            message = "Classroom deleted successfully"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable UUID id) {
        return classroomService.findClassroomById(id)
                .map(student -> {
                    classroomService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
