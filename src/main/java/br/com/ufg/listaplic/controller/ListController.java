package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.service.ListApplicationService;
import br.com.ufg.listaplic.service.ListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lists")
@Api(value = "List")
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private ListApplicationService listApplicationService;

    @ApiOperation(
            value = "Get lists by filter",
            responseContainer = "list",
            response = ListDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get lists by filter",
            responseContainer = "list",
            response = ListDTO.class
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListDTO> getListsByFilter(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "subjectCode", required = false) String subjectCode) {
        return listService.getListsByFilter(name, subjectCode);
    }

    @ApiOperation(
            value = "Get pending lists by student",
            responseContainer = "list",
            response = ListDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get pending lists by student",
            responseContainer = "list",
            response = ListDTO.class
    )
    @GetMapping("/pending")
    @ResponseStatus(HttpStatus.OK)
    public List<ListDTO> getPendingListsByStudent(@RequestParam(value = "studentId") UUID studentId) {
        return listService.getPendingListsByStudent(studentId);
    }

    @GetMapping("/applications/by-classroom")
    @ResponseStatus(HttpStatus.OK)
    public List<ListApplicationDTO> getFinishedApplicationsByClassroomId(@RequestParam(value = "classroomId") UUID classroomId) {
        return listApplicationService.getFinishedListsByClassroomId(classroomId);
    }

    @ApiOperation(
            value = "Answering the list"
    )
    @ApiResponse(
            code = 201,
            message = "Answering the list"
    )
    @PostMapping("/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public void answeringList(@RequestParam("studentId") UUID studentId,
                              @RequestBody @Valid ListDTO listDTO) {
        listService.answeringList(studentId, listDTO);
    }

    @ApiOperation(
            value = "Apply list to determinated group"
    )
    @ApiResponse(
            code = 200,
            message = "Applied list to group."
    )
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity applyListToGroup(@RequestBody @Valid ApplyDTO applyDTO) {
        listApplicationService.applyListTo(applyDTO);
        return ResponseEntity.ok().build();
    }

}
