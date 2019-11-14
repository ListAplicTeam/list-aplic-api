package br.com.ufg.listaplic.dto.listelab;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListElabSingleQuestionResultDTO {
    private String mensagem;
    private Boolean sucesso;
    private QuestaoIntegrationDTO resultado;

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

    public QuestaoIntegrationDTO getResultado() {
        return resultado;
    }

    public void setResultado(QuestaoIntegrationDTO resultado) {
        this.resultado = resultado;
    }
}
