package br.com.ufg.listaplic.dto.listelab;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListElabResultDTO {

    private String mensagem;
    private Boolean sucesso;
    private List<ListIntegrationDTO> resultado;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public List<ListIntegrationDTO> getResultado() {
        return resultado;
    }

    public void setResultado(List<ListIntegrationDTO> resultado) {
        this.resultado = resultado;
    }
}
