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

	@Value("${listelab.api.url.discursive-question}")
	private String apiDiscursiveQuestionUrl;

	@Value("${listelab.api.url.objective-question}")
	private String apiObjectiveQuestionUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ListDTO> getLists() {
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.AUTHORIZATION, BEARER + getApiKey());
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ListElabResultDTO listElabResultDTO = restTemplate.exchange(apiListUrl, HttpMethod.GET, entity, ListElabResultDTO.class).getBody();
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

}
