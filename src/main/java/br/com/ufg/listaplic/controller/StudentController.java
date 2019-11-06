package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.EnrollmentDTO;
import br.com.ufg.listaplic.dto.StudentDTO;
import br.com.ufg.listaplic.service.EnrollmentService;
import br.com.ufg.listaplic.service.StudentService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@Api(value = "Student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentService enrollmentService;

    @ApiOperation(
            value = "Get All Students",
            responseContainer = "list",
            response = StudentDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get All Students",
            responseContainer = "list",
            response = StudentDTO.class
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findAll() {
        return studentService.findAll();
    }

    @ApiOperation(
            value = "Get Student by id",
            response = StudentDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get Student by id",
            response = StudentDTO.class
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO findById(@PathVariable("id") UUID id) {
        return studentService.findById(id);
    }

    @ApiOperation(
            value = "Create Student",
            response = StudentDTO.class
    )
    @ApiResponse(
            code = 201,
            message = "Student created successfully.",
            response = StudentDTO.class
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO save(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @ApiOperation(
            value = "Update Student",
            response = StudentDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Student updated successfully",
            response = StudentDTO.class
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO update(@PathVariable("id") UUID id,
                             @RequestBody @Valid StudentDTO studentDTO) {
        return studentService.update(id, studentDTO);
    }

    @ApiOperation(
            value = "Delete Student by id"
    )
    @ApiResponse(
            code = 200,
            message = "Student deleted successfully"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable UUID id) {
        return studentService.findStudentById(id)
                .map(student -> {
                    studentService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(
            value = "Enroll Student"
    )
    @ApiResponse(
            code = 200,
            message = "Student enrolled successfully."
    )
    @PostMapping("/{id}/enrollment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity enrollment(@PathVariable("id") UUID id,
                                     @RequestBody EnrollmentDTO enrollmentDTO) {
        enrollmentService.enrollment(id, enrollmentDTO);
        return ResponseEntity.ok().build();
    }

}
