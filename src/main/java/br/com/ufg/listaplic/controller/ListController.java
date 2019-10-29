package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.service.ListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lists")
@Api(value = "List")
public class ListController {

    @Autowired
    private ListService listService;

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

}
