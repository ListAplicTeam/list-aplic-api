package br.com.ufg.listaplic.network;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.LoginDTO;
import br.com.ufg.listaplic.dto.listelab.UserIntegrationDTO;

import java.util.List;

public interface ListElabNetwork {

    List<ListDTO> getLists();

    UserIntegrationDTO login(LoginDTO loginDTO);
}
