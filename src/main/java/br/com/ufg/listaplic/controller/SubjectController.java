package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.SubjectDTO;
import br.com.ufg.listaplic.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@Api(value = "Subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(
            value = "Get All Subjects",
            responseContainer = "list",
            response = SubjectDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get All Subjects",
            responseContainer = "list",
            response = SubjectDTO.class
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDTO> findAll() {
        return subjectService.getSubjects();
    }

}
