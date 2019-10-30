package br.com.ufg.listaplic.network.impl;

import br.com.ufg.listaplic.converter.ListConverterDTO;
import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.listelab.AuthenticationDTO;
import br.com.ufg.listaplic.dto.listelab.ListElabResultDTO;
import br.com.ufg.listaplic.dto.listelab.UserIntegrationDTO;
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
