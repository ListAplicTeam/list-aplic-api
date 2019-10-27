package br.com.ufg.listaplic.converter;

import br.com.ufg.listaplic.dto.ListDTO;
import br.com.ufg.listaplic.dto.listelab.ListIntegrationDTO;

public final class ListConverterDTO {

    private ListConverterDTO() {
    }

    public static ListDTO fromListIntegrationToDTO(final ListIntegrationDTO listIntegrationDTO) {
        ListDTO listDTO = new ListDTO();
        listDTO.setId(listIntegrationDTO.getId());
        listDTO.setName(listIntegrationDTO.getTitulo());
        listDTO.setUser(listIntegrationDTO.getDiscursivas().stream().findFirst().get().getUsuario());
        listDTO.setSubjectCode(listIntegrationDTO.getDisciplina().toString());
        return listDTO;
    }

}
