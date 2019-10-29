package br.com.ufg.listaplic.controller;

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
            value = "Get Lists by Instructor And Filter",
            responseContainer = "list",
            response = ListDTO.class
    )
    @ApiResponse(
            code = 200,
            message = "Get Lists by Instructor And Filter",
            responseContainer = "list",
            response = ListDTO.class
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListDTO> findList(@RequestParam("user") String user,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "subjectCode", required = false) String subjectCode,
                                  @RequestParam(value = "aleatory", required = false) boolean aleatory) {
        return listService.findListByInstructor(user, name, subjectCode, aleatory);
    }

    @ApiOperation(
            value = "Answering the list"
    )
    @ApiResponse(
            code = 201,
            message = "Answering the list"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void answeringList(@RequestParam("userId") UUID userId,
                              @RequestBody @Valid ListDTO listDTO) {
        listService.answeringList(userId, listDTO);
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
    public ResponseEntity applyListToGroup(@RequestBody Boolean allClassroom,
                                           @RequestBody String group,
                                           @RequestBody UUID classroomId,
                                           @RequestBody UUID list) {
        listApplicationService.applyListTo(allClassroom, group, classroomId, list);
        return ResponseEntity.ok().build();
    }


}
