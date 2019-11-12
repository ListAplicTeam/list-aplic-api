package br.com.ufg.listaplic.network;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.QuestionDTO;
import br.com.ufg.listaplic.dto.listelab.UserIntegrationDTO;

import java.util.List;
import java.util.UUID;

public interface ListElabNetwork {

    List<ListDTO> getLists();

    UserIntegrationDTO login(LoginDTO loginDTO);

    ListDTO getListById(UUID id);

    QuestionDTO getQuestionById(UUID id);
}
