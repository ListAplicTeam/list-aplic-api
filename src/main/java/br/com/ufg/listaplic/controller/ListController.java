package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.ApplyDTO;
import br.com.ufg.listaplic.dto.ListApplicationDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.model.ApplicationListStatus;
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
	public List<ListDTO> getListsByFilter(@RequestParam(value = "subjectCode", required = false) String subjectCode,
										  @RequestParam(value = "difficultyLevel", required = false) Integer difficultyLevel,
										  @RequestParam(value = "knowledgeAreaCode", required = false) String knowledgeAreaCode,
										  @RequestParam(value = "answerTime", required = false) Integer answerTime,
										  @RequestParam(value = "tags", required = false) List<String> tags) {
		return listService.getListsByFilter(subjectCode, difficultyLevel, knowledgeAreaCode, answerTime, tags);
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

	@ApiOperation(
			value = "Get applications by classroom",
			responseContainer = "list",
			response = ListApplicationDTO.class
	)
	@ApiResponse(
			code = 200,
			message = "Applications by classroom.",
			responseContainer = "list",
			response = ListApplicationDTO.class
	)
	@GetMapping("/applications/{classroomId}")
	@ResponseStatus(HttpStatus.OK)
	public List<ListApplicationDTO> getApplicationsByClassroom(@PathVariable("classroomId") UUID classroomId,
															   @RequestParam(value = "status", required = false) String status) {
		ApplicationListStatus applicationListStatus = null;

		try {
			applicationListStatus = status == null ? null : ApplicationListStatus.valueOf(status);
		} catch (Exception e) {
			throw new IllegalArgumentException("The status param is invalid");
		}

		return listApplicationService.getListsByClassroom(classroomId, applicationListStatus);
	}

	@ApiOperation(
			value = "Get list application detail",
			response = ListApplicationDTO.class
	)
	@ApiResponse(
			code = 200,
			message = "List application detail.",
			response = ListApplicationDTO.class
	)
	@GetMapping("/application/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ListApplicationDTO getApplicationDetailById(@PathVariable("id") UUID applicationId) {
		return listApplicationService.getListApplicationDetail(applicationId);
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
