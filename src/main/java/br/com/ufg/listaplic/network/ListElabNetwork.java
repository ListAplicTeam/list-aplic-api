package br.com.ufg.listaplic.network;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.LoginDTO;

import java.util.List;
import java.util.UUID;

public interface ListElabNetwork {

    List<ListDTO> getLists();

    String login(LoginDTO loginDTO);

    ListDTO getListById(UUID id);
}
