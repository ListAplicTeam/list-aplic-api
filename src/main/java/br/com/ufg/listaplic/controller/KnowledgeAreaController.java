package br.com.ufg.listaplic.controller;

import br.com.ufg.listaplic.dto.listelab.AreaDoConhecimentoDTO;
import br.com.ufg.listaplic.network.ListElabNetwork;
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
@RequestMapping("/knowledge-areas")
@Api(value = "Knowledges")
public class KnowledgeAreaController {

	@Autowired
	private ListElabNetwork listElabNetwork;

	@ApiOperation(
			value = "Get All Knowledge Areas",
			responseContainer = "list",
			response = AreaDoConhecimentoDTO.class
	)
	@ApiResponse(
			code = 200,
			message = "Get All Knowledge Areas",
			responseContainer = "list",
			response = AreaDoConhecimentoDTO.class
	)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AreaDoConhecimentoDTO> findAll() {
		return listElabNetwork.getAllKnowledgeAreas();
	}

}
