package br.com.ufg.listaplic.dto.listelab;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListElabSingleResultDTO {

    private String mensagem;
    private Boolean sucesso;
    private ListIntegrationDTO resultado;

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

    public ListIntegrationDTO getResultado() {
        return resultado;
    }

    public void setResultado(ListIntegrationDTO resultado) {
        this.resultado = resultado;
    }
}
