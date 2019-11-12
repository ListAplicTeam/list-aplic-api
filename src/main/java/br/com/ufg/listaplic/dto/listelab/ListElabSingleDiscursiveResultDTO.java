package br.com.ufg.listaplic.dto.listelab;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListElabSingleDiscursiveResultDTO {
    private String mensagem;
    private Boolean sucesso;
    private DiscursivasIntegrationDTO resultado;

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

    public DiscursivasIntegrationDTO getResultado() {
        return resultado;
    }

    public void setResultado(DiscursivasIntegrationDTO resultado) {
        this.resultado = resultado;
    }
}
