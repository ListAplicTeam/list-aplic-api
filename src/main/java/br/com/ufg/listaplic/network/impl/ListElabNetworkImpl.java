package br.com.ufg.listaplic.network.impl;

import br.com.ufg.listaplic.converter.ListConverterDTO;
import br.com.ufg.listaplic.converter.QuestionConverterDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.*;
import br.com.ufg.listaplic.exception.NetworkException;
import br.com.ufg.listaplic.network.ListElabNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ListElabNetworkImpl implements ListElabNetwork {

	private static final String BEARER = "Bearer ";

	@Value("${listelab.api.auth.email}")
	private String email;

	@Value("${listelab.api.auth.password}")
	private String password;

	@Value("${listelab.api.url.auth}")
	private String authUrl;

	@Value("${listelab.api.url.list}")
	private String apiListUrl;

	@Value("${listelab.api.url.filter-list}")
	private String apiFilterListUrl;

	@Value("${listelab.api.url.discursive-question}")
	private String apiDiscursiveQuestionUrl;

	@Value("${listelab.api.url.objective-question}")
	private String apiObjectiveQuestionUrl;

	@Value("${listelab.api.url.knowledge-area}")
	private String apiKnowledgeAreaUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ListDTO> getListsByFilter(FilterList filterList) {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.AUTHORIZATION, BEARER + getApiKey());

			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = buildUriComponentsBuilderForFilterList(filterList, apiFilterListUrl);

			ListElabResultDTO listElabResultDTO = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ListElabResultDTO.class).getBody();
			return listElabResultDTO.getResultado().stream()
					.map(ListConverterDTO::fromListIntegrationToListDTO)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new NetworkException("Failed to get list in ListElab service", e);
		}
	}

	@Override
	public ListDTO getListById(UUID id) {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.AUTHORIZATION, BEARER + getApiKey());
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ListElabSingleResultDTO listElabSingleResultDTO = restTemplate.exchange(apiListUrl + "/" + id, HttpMethod.GET, entity, ListElabSingleResultDTO.class).getBody();

			return ListConverterDTO.fromListIntegrationToListDTO(listElabSingleResultDTO.getResultado());
		} catch (Exception e) {
			throw new NetworkException("Failed to get list in ListElab service", e);
		}
	}

	@Override
	public List<AreaDoConhecimentoDTO> getAllKnowledgeAreas() {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.AUTHORIZATION, BEARER + getApiKey());
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ListElabKnowledgeAreaResultDTO areaDoConhecimentoDTOS = restTemplate.exchange(apiKnowledgeAreaUrl, HttpMethod.GET, entity, ListElabKnowledgeAreaResultDTO.class).getBody();

			return areaDoConhecimentoDTOS.getResultado();
		} catch (Exception e) {
			throw new NetworkException("Failed to get list in ListElab service", e);
		}
	}

	@Override
	public QuestionDTO getQuestionById(UUID id) {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.AUTHORIZATION, BEARER + getApiKey());
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ListElabSingleQuestionResultDTO questionResultDTO = restTemplate.exchange(apiDiscursiveQuestionUrl + "/" + id, HttpMethod.GET, entity, ListElabSingleQuestionResultDTO.class).getBody();
			return questionResultDTO.getResultado() != null ? QuestionConverterDTO.fromDomainToDTO(questionResultDTO.getResultado()) : new QuestionDTO();
		} catch (Exception e) {
			throw new NetworkException("Failed to get question in ListElab service", e);
		}
	}

	@Override
	public UserIntegrationDTO login(LoginDTO loginDTO) {
		HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDTO);

		AuthenticationDTO authenticationDTO = restTemplate.exchange(this.authUrl, HttpMethod.POST, entity, AuthenticationDTO.class).getBody();
		return Optional.ofNullable(authenticationDTO).map(AuthenticationDTO::getResultado).orElseThrow(() -> new NetworkException("Invalid username or password"));
	}

	private String getApiKey() {
		try {
			return login(getLoginAdmin()).getToken();
		} catch (Exception e) {
			throw new NetworkException("Failed to get token in ListElab service", e);
		}
	}

	private LoginDTO getLoginAdmin() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(this.email);
		loginDTO.setPassword(this.password);
		return loginDTO;
	}

	private UriComponentsBuilder buildUriComponentsBuilderForFilterList(FilterList filterList, String apiUrl) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiFilterListUrl);

		if (filterList.getAreaDeConhecimento() != null) {
			builder.queryParam("areaDeConhecimento", filterList.getAreaDeConhecimento());
		}

		if (filterList.getDisciplina() != null) {
			builder.queryParam("disciplina", filterList.getDisciplina());
		}

		if (filterList.getNivelDificuldade() != null) {
			builder.queryParam("nivelDificuldade", filterList.getNivelDificuldade());
		}

		if (filterList.getTempoEsperadoResposta() != null) {
			builder.queryParam("tempoEsperadoResposta", filterList.getTempoEsperadoResposta());
		}

		if (filterList.getTags() != null && filterList.getTags().size() > 0) {
			builder.queryParam("tags", filterList.getTags().toArray());
		}

		return builder;
	}

}
